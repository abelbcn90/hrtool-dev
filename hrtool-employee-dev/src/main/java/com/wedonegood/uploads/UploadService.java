package com.wedonegood.uploads;

import java.io.IOException;
import java.io.InputStream;

public interface UploadService {
    String createProfilePicture(final Long userId, final String contentType, final InputStream file) throws IOException;
    String createPassportScan(final Long employeeId, final String contentType, final InputStream file) throws IOException;
    String createPassportScanPdf(final Long employeeId, final String contentType, final InputStream file) throws IOException;
    String getFilePathProfilePicture(final Long userId, final String fileName);
    String getFilePathPassportScan(final Long employeeId, final String fileName);
    void deletePassportScan(final Long employeeId, final String filePath);
}
