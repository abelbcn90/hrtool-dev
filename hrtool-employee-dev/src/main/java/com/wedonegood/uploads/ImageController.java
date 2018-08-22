package com.wedonegood.uploads;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Transactional
@RestController
@RequestMapping(value = "/api/v1/img")
@Api(value="Image", description="Operations pertaining to images", position = 6)
public class ImageController {

	final static int SCALED_SIZE_BIG = 138;
	final static int SCALED_SIZE_SMALL = 44;
	final static int SCALED_SIZE_PASSPORT = 99;
	
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/profilePicture/{employeeId}/small", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get small profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get small profile picture")
    })
    public ResponseEntity getSmallProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
        final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(employee.getProfilePicture());
        final String contentType = Files.probeContentType(file.toPath());
        
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(this.resizeImage(file, SCALED_SIZE_SMALL, SCALED_SIZE_SMALL), file.getName().substring(file.getName().lastIndexOf(".") + 1), baos);
        
        final byte[] bytes = baos.toByteArray();
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(bytes);
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/big", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get big profile picture")
    })
    public ResponseEntity getBigProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
        final File file = new File(employee.getProfilePicture());
        final String contentType = Files.probeContentType(file.toPath());
        
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(this.resizeImage(file, SCALED_SIZE_BIG, SCALED_SIZE_BIG), file.getName().substring(file.getName().lastIndexOf(".") + 1), baos);
        
        final byte[] bytes = baos.toByteArray();
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(bytes);
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/full", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get full profile picture")
    })
    public ResponseEntity getProfilePictureFull(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(employee.getProfilePicture());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/preview", produces = {"image/png", "image/jpg", "application/pdf"})
    @ApiOperation(value = "Get passport scan preview", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan preview")
    })
    public ResponseEntity getPassportScanPreview(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(employee.getPassportScan());
    	final String contentType = Files.probeContentType(file.toPath());
    	
    	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	
    	if (!employee.getPassportScan().substring(employee.getPassportScan().lastIndexOf(".") + 1).equals("pdf")) {
    		ImageIO.write(this.resizeImage(file, SCALED_SIZE_PASSPORT, SCALED_SIZE_PASSPORT), file.getName().substring(file.getName().lastIndexOf(".") + 1), baos);
    		
    		return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(baos.toByteArray());
    		
    	} else {
    		final PDDocument pdDocument = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
    		final PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
    		
    		ImageIO.write(this.resizeImagePdf(pdfRenderer, SCALED_SIZE_PASSPORT, SCALED_SIZE_PASSPORT, 0), "jpg", baos);
    		
    		pdDocument.close();
    		
    		return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(baos.toByteArray());
    	}
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/full", produces = {"image/png", "image/jpg", "application/pdf"})
    @ApiOperation(value = "Get passport scan", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan full")
    })
    public ResponseEntity getPassportScanFull(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(employee.getPassportScan());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
    
    /**
     * 
     * @param file
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
	public BufferedImage resizeImage(final File file, final int width, final int height) throws IOException {
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    final Graphics2D normalGraphics2D = bufferedImage.createGraphics(); //create a graphics object to paint to
	    normalGraphics2D.setBackground(Color.WHITE);
	    normalGraphics2D.setPaint(Color.WHITE);
	    normalGraphics2D.fillRect(0, 0, width, height);
	    normalGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    
	    final Image image = ImageIO.read(file);
	    
	    final Dimension pageSize = new Dimension(image.getWidth(null), image.getHeight(null));
	    final Dimension boundary = new Dimension(width, height);
	    
	    final Dimension pageScaledSize = this.getScaledDimension(pageSize, boundary);
	    final Double pageWith = pageScaledSize.getWidth();
	    final Double pageHeight = pageScaledSize.getHeight();
	    
	    final int x = (width - pageWith.intValue()) / 2;
	    normalGraphics2D.drawImage(image, x, 0, pageWith.intValue(), pageHeight.intValue(), null); //draw the image scaled
		
		return bufferedImage;
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
	public BufferedImage resizeImagePdf(final PDFRenderer pdfRenderer, final int width, final int height, final int pageIndex) throws IOException {
	    final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    final Graphics2D normalGraphics2D = bufferedImage.createGraphics(); //create a graphics object to paint to
	    normalGraphics2D.setBackground(Color.WHITE);
	    normalGraphics2D.setPaint(Color.WHITE);
	    normalGraphics2D.fillRect(0, 0, width, height);
	    normalGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    
	    final Image page = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);
	    
	    final Dimension pageSize = new Dimension(page.getWidth(null), page.getHeight(null));
	    final Dimension boundary = new Dimension(width, height);
	    
	    final Dimension pageScaledSize = this.getScaledDimension(pageSize, boundary);
	    final Double pageWith = pageScaledSize.getWidth();
	    final Double pageHeight = pageScaledSize.getHeight();
	    
	    final int x = (width - pageWith.intValue()) / 2;
	    normalGraphics2D.drawImage(page, x, 0, pageWith.intValue(), pageHeight.intValue(), null); //draw the image scaled
		
		return bufferedImage;
	}
	
	/**
	 * 
	 * @param imgSize
	 * @param boundary
	 * @return
	 */
	public Dimension getScaledDimension(final Dimension imgSize, final Dimension boundary) {
	    final int original_width = imgSize.width;
	    final int original_height = imgSize.height;
	    final int bound_width = boundary.width;
	    final int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
}
