package com.spring.deduplicationweb.Chunks;

public class ChunkCount
{
    public String shaValue;
    public int shaCount;

    public ChunkCount(){}
    public ChunkCount(String shaValue, int shaCount) {
        this.shaValue = shaValue;
        this.shaCount = shaCount;
    }

    public String getShaValue() {
        return shaValue;
    }

    public void setShaValue(String shaValue) {
        this.shaValue = shaValue;
    }

    public int getShaCount() {
        return shaCount;
    }

    public void setShaCount(int shaCount) {
        this.shaCount = shaCount;
    }
}
