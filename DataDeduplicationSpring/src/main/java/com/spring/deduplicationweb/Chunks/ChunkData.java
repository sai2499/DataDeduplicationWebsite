package com.spring.deduplicationweb.Chunks;

public class ChunkData 
{
	public int shaId;
	public String rollinghash;
	public String valueSha;
	public Long chuckSize;
	
	public ChunkData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChunkData(int shaId, String rollinghash, String valueSha,Long chuckSize) {
		super();
		this.shaId = shaId;
		this.rollinghash = rollinghash;
		this.valueSha = valueSha;
		this.chuckSize=chuckSize;
	}

	public int getShaId() {
		return shaId;
	}

	public void setShaId(int shaId) {
		this.shaId = shaId;
	}

	public String getRollinghash() {
		return rollinghash;
	}

	public void setRollinghash(String rollinghash) {
		this.rollinghash = rollinghash;
	}

	public String getValueSha() {
		return valueSha;
	}

	public void setValueSha(String valueSha) {
		this.valueSha = valueSha;
	}


	public Long getChuckSize() {
		return chuckSize;
	}

	public void setChuckSize(Long chuckSize) {
		this.chuckSize = chuckSize;
	}
}
