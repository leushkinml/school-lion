package ru.hogwarts.schoollion.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;

    private String filePath;
    private Long fileSize;

    private String mediaType;

    @Lob
    private byte[] previewAvatar;

    @OneToOne
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getPreviewAvatar() {
        return previewAvatar;
    }

    public void setPreviewAvatar(byte[] previewAvatar) {
        this.previewAvatar = previewAvatar;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar avatar)) return false;
        return Objects.equals(getId(), avatar.getId()) && Objects.equals(getFilePath(), avatar.getFilePath()) && Objects.equals(getFileSize(), avatar.getFileSize()) && Objects.equals(getMediaType(), avatar.getMediaType()) && Arrays.equals(getPreviewAvatar(), avatar.getPreviewAvatar()) && Objects.equals(getStudent(), avatar.getStudent());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getFilePath(), getFileSize(), getMediaType(), getStudent());
        result = 31 * result + Arrays.hashCode(getPreviewAvatar());
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", previewAvatar=" + Arrays.toString(previewAvatar) +
                ", student=" + student +
                '}';
    }
}
