package com.spring.deduplicationweb.FileDetails;

import java.util.Date;

public class FileDetails 
{

	public int fileid;
	public String filename;
	public Date filedate;
	public Long filesize;
	
	public FileDetails() {
	}
	public FileDetails(int fileid, String filename, Date filedate,Long filesize) {
		super();
		this.fileid = fileid;
		this.filename = filename;
		this.filedate = filedate;
		this.filesize = filesize;
	}
	public int getId() {
		return fileid;
	}
	public void setId(int id) {
		this.fileid = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getFiledate() {
		return filedate;
	}
	public void setFiledate(Date filedate) {
		this.filedate = filedate;
	}
	
	@Override
	public String toString() {
		return "FileDetails [id=" + fileid + ", filename=" + filename + ", filedate=" + filedate + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fileid;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDetails other = (FileDetails) obj;
		if (fileid != other.fileid)
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		return true;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
}
