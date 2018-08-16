package com.wedonegood.uploads;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface UploadService {
    String createProfilePicture(final Long employeeId, final String originalFileName, final InputStream file) throws IOException;
    String createPassportScan(final Long employeeId, final String originalFileName, final InputStream file) throws IOException;
    File getMediaFilePath(final Long employeeId, final String fileName);
}
