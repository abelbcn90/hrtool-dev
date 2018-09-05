package com.wedonegood.uploads;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.net.MediaType;

@Service
public class UploadServiceImpl implements UploadService {

	private final static String FOLDER_HOME = "folder.home";
    private final static String FOLDER_UPLOADS = "folder.uploads";
    private final static String FOLDER_PROFILE_PICTURE = "folder.profile.picture";
    private final static String FOLDER_PASSPORT_SCAN = "folder.passport.scan";
    private final static String FILE_NAME_PROFILE_PICTURE = "file.name.profile.picture";
    private final static String FILE_NAME_PASSPORT_SCAN = "file.name.passport.scan";
    private final static String FILE_NAME_SMALL = "file.name.small";
    private final static String FILE_NAME_MEDIUM = "file.name.medium";
    private final static String FILE_NAME_BIG = "file.name.big";
    private final static String FILE_NAME_PREVIEW = "file.name.preview";
    private final static String FILE_SIZE_SMALL = "file.size.small";
    private final static String FILE_SIZE_MEDIUM = "file.size.medium";
    private final static String FILE_SIZE_BIG = "file.size.big";
	private final static String FILE_SIZE_PREVIEW = "file.size.preview";
	final static int SCALED_SIZE_SMALL = 44;
	final static int SCALED_SIZE_MEDIUM = 56;
	final static int SCALED_SIZE_BIG = 138;
	final static int SCALED_SIZE_PREVIEW = 99;
    
    @Autowired
	private Environment env;

    @Override
    public String createProfilePicture(final Long userId, final InputStream file) throws IOException {
    	final String extention = "." + MediaType.PNG.subtype();
    	final File f = new File(this.getFilePathProfilePicture(userId, this.env.getProperty(FILE_NAME_PROFILE_PICTURE) + extention));
        
	    f.getParentFile().mkdirs();
	    Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
	    final BufferedImage image = ImageIO.read(f);
	    
	    ImageIOUtil.writeImage(this.resizeImage(image, SCALED_SIZE_SMALL, SCALED_SIZE_SMALL), this.getFilePathProfilePicture(
	    		userId, this.env.getProperty(FILE_NAME_SMALL) + extention), 300);
	    ImageIOUtil.writeImage(this.resizeImage(image, SCALED_SIZE_MEDIUM, SCALED_SIZE_MEDIUM), this.getFilePathProfilePicture(
	    		userId, this.env.getProperty(FILE_NAME_MEDIUM) + extention), 300);
	    ImageIOUtil.writeImage(this.resizeImage(image, SCALED_SIZE_BIG, SCALED_SIZE_BIG), this.getFilePathProfilePicture(
	    		userId, this.env.getProperty(FILE_NAME_BIG) + extention), 300);
	    
        return f.getPath();
    }
    
    @Override
    public String createPassportScan(final Long employeeId, final InputStream file) throws IOException {
    	final String extention = "." + MediaType.PNG.subtype();
    	final File f = new File(this.getFilePathPassportScan(employeeId, this.env.getProperty(FILE_NAME_PASSPORT_SCAN) + extention));
    	
    	f.getParentFile().mkdirs();
	    Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    	
    	ImageIOUtil.writeImage(this.resizeImage(ImageIO.read(f), SCALED_SIZE_PREVIEW, SCALED_SIZE_PREVIEW), this.getFilePathPassportScan(
    			employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + extention), 300);
    	
    	return f.getPath();
    }
    
    @Override
    public String createPassportScanPdf(final Long employeeId, final InputStream file) throws IOException {
    	final String extention = "." + MediaType.PDF.subtype();
    	final File f = new File(this.getFilePathPassportScan(employeeId, this.env.getProperty(FILE_NAME_PASSPORT_SCAN) + extention));
    	
    	f.getParentFile().mkdirs();
    	Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    	
    	final PDDocument pdDocument = PDDocument.load(f, MemoryUsageSetting.setupTempFileOnly());
		final PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
		
		ImageIOUtil.writeImage(this.resizeImagePdf(pdfRenderer, SCALED_SIZE_PREVIEW, SCALED_SIZE_PREVIEW, 0), this.getFilePathPassportScan(
				employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + "." + MediaType.PNG.subtype()), 300);
		
		pdDocument.close();
    	
    	return f.getPath();
    }
    
    @Override
    public String getFilePathProfilePicture(final Long userId, final String fileName) {
    	return this.env.getProperty(FOLDER_UPLOADS) + this.env.getProperty(FOLDER_PROFILE_PICTURE) + userId + "/" + fileName;
    }
    
    @Override
    public String getFilePathPassportScan(final Long employeeId, final String fileName) {
    	return this.env.getProperty(FOLDER_UPLOADS) + this.env.getProperty(FOLDER_PASSPORT_SCAN) + employeeId + "/" + fileName;
    }
    
    @Override
    public void deletePassportScan(final Long employeeId, final String filePath) {
    	final File file = new File(filePath);
    	file.delete();
    	
    	final File preview = new File(this.getFilePathPassportScan(employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + "." + MediaType.PNG.subtype()));
    	preview.delete();
    }
    
    /**
     * 
     * @param file
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
	private BufferedImage resizeImage(final BufferedImage originalImage, final int width, final int height) throws IOException {
		Image image = originalImage;
		
		if (originalImage.getWidth() > originalImage.getHeight()) {
	    	System.out.println("WIDTH");
	    	image = originalImage.getScaledInstance(-1, height, Image.SCALE_DEFAULT);
	    	
	    } else if (originalImage.getWidth() < originalImage.getHeight()) {
	    	System.out.println("WIDTH");
	    	image = originalImage.getScaledInstance(width, -1, Image.SCALE_DEFAULT);
	    } else {
	    	return Scalr.resize(originalImage, Method.QUALITY, width, height);
	    }
		
	    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bufferedImage.createGraphics();
	    bGr.drawImage(image, 0, 0, null);
	    bGr.dispose();
		
		int originalWidth = bufferedImage.getWidth();
	    int originalHeight = bufferedImage.getHeight();

	    int newWidth;
	    int newHeight;
	    if (originalWidth == 0 || originalHeight == 0
	            || (originalWidth == width && originalHeight == height)) {
	        return originalImage;
	    }

	    double aspectRatio = (double) originalWidth / (double) originalHeight;
	    double boundaryAspect = (double) width / (double) height;

	    if (aspectRatio > boundaryAspect) {
	        newWidth = width;
	        newHeight = (int) Math.round(newWidth / aspectRatio);
	    } else {
	        newHeight = height;
	        newWidth = (int) Math.round(aspectRatio * newHeight);
	    }

	    int xOffset = (originalWidth - newWidth) / 2;
	    int yOffset = (originalHeight - newHeight) / 2;
	    
	    BufferedImage bufferedImageResult = bufferedImage;
	    
	    if (originalWidth > originalHeight) {
	    	bufferedImageResult = bufferedImage.getSubimage(xOffset, 0, width, originalHeight);
	    	
	    } else if (originalWidth < originalHeight) {
	    	bufferedImageResult = bufferedImage.getSubimage(0, yOffset, originalWidth, height);
	    }
	    
	    return bufferedImageResult;
	}
	
	/**
	 * 
	 * @param pdfRenderer
	 * @param width
	 * @param height
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 */
	private BufferedImage resizeImagePdf(final PDFRenderer pdfRenderer, final int width, final int height, final int pageIndex) throws IOException {
		final BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(pageIndex, 300);

	    return this.resizeImage(bufferedImage, width, height);
	}
}
