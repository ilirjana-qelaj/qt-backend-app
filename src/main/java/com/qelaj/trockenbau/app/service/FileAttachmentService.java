package com.qelaj.trockenbau.app.service;

import com.qelaj.trockenbau.app.dto.FileAttachmentBodyDTO;
import com.qelaj.trockenbau.app.entity.FileAttachment;
import com.qelaj.trockenbau.app.repository.FileAttachmentRepository;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FileAttachmentService {

    private final FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    public FileAttachmentService(FileAttachmentRepository fileAttachmentRepository) {
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public void saveFile(MultipartFile multipartFile) throws IOException, SQLException {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setFileName(multipartFile.getOriginalFilename());
        fileAttachment.setFileType(multipartFile.getContentType());

        byte[] bytes = multipartFile.getBytes();
        Blob blob = new SerialBlob(bytes);
        fileAttachment.setFileContent(blob);

        fileAttachmentRepository.save(fileAttachment);
    }

    public List<FileAttachment> getFiles(){
        return fileAttachmentRepository.findAll();
    }

    public int updateDescription(Long id, FileAttachmentBodyDTO attachmentBodyDTO){
        Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findById(id);
        if(fileAttachment.isPresent()){
            fileAttachment.get().setFileDescriptionEN(attachmentBodyDTO.getFileDescriptionEN());
            fileAttachment.get().setFileHeaderEN(attachmentBodyDTO.getFileHeaderEN());
            fileAttachment.get().setFileDescriptionDE(attachmentBodyDTO.getFileDescriptionDE());
            fileAttachment.get().setFileHeaderDE(attachmentBodyDTO.getFileHeaderDE());
            fileAttachmentRepository.save(fileAttachment.get());
            return 200;
        }
        return 404;
    }

    public int deleteFile(Long id) {
        Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findById(id);
        if(fileAttachment.isPresent()){
            fileAttachmentRepository.deleteById(id);
            return 200;
        }
        return 404;
    }
}
