package com.experta.pap.model;

public class FileInfo {

	private String _fileName;
	private long _fileSize;

	public FileInfo() {

	}

	public String get_fileName() {
		return _fileName;
	}

	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}

	public long get_fileSize() {
		return _fileSize;
	}

	public void set_fileSize(long _fileSize) {
		this._fileSize = _fileSize;
	}

	@Override
	public String toString() {
		return "FileInfo [_fileName=" + _fileName + ", _fileSize=" + _fileSize + "]";
	}

}
