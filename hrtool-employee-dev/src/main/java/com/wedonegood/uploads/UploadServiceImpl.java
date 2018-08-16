package com.wedonegood.uploads;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    private final static String UPLOADS_FOLDER = "uploads.folder";
    private final static String FILE_NAME_PROFILE_PICTURE = "file.name.profile.picture";
    private final static String FILE_NAME_PASSPORT_SCAN = "file.name.passport.scan";
    
    @Autowired
	private Environment env;

    @Override
    public String createProfilePicture(final Long employeeId, final String originalFileName, final InputStream file) throws IOException {
        final File f = this.getMediaFilePath(employeeId, this.env.getProperty(FILE_NAME_PROFILE_PICTURE) + originalFileName.subSequence(originalFileName.indexOf("."), originalFileName.length()));
        
        f.getParentFile().mkdirs();
        Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        return f.getPath();
    }
    
    @Override
    public String createPassportScan(final Long employeeId, final String originalFileName, final InputStream file) throws IOException {
    	final File f = this.getMediaFilePath(employeeId, this.env.getProperty(FILE_NAME_PASSPORT_SCAN) + originalFileName.subSequence(originalFileName.lastIndexOf("."), originalFileName.length()));
    	
    	f.getParentFile().mkdirs();
    	Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    	
    	return f.getPath();
    }
    
    @Override
    public File getMediaFilePath(final Long employeeId, final String fileName) {
        return new File(this.env.getProperty(UPLOADS_FOLDER) + employeeId + "\\" + fileName);
    }
}
