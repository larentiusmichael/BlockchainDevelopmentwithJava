package Blockchain;
import Blockchain.Tools.Hashing;
import Blockchain.Tools.Salt;

import java.io.Serial;
import java.io.Serializable;

public class Block implements Serializable {
    //    @Serial
//    private static final long serialVersionUID = 7884373060987423461L;
    public Header blockHeader;
    public Header getBlockHeader() {
        return blockHeader;
    }

    public Block(String previousHash, String merkleRoot, int previousIndex) {
        super();
        long now = System.currentTimeMillis();
        byte[] salt = Salt.generate();

        this.blockHeader = new Header();
        this.blockHeader.setPreviousHash(previousHash);
        this.blockHeader.setTimestamp(now);
        this.blockHeader.setIndex(previousIndex);
        this.blockHeader.setNonce(salt);
        //hashing with sha256 - the input is joined with previousHash+now+merkleroot
        String currentHash = Hashing.sha256(
                String.join("+", previousHash, String.valueOf(now), merkleRoot),
                salt);
        this.blockHeader.setCurrentHash(currentHash);
    }

    public class Header implements Serializable{
        @Serial
        private static final long serialVersionUID = 7884373060987423461L;
        //data member
        private int index;
        private String currentHash, previousHash;
        private long timestamp;
        private byte[] nonce;

        @Override
        public String toString() {
            return "Header [index=" + index +
                    ", currentHash=" + currentHash +
                    ", previousHash=" + previousHash +
                    ", timestamp=" + timestamp +
                    ", nonce=" + nonce + "]";
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public String getCurrentHash() {
            return currentHash;
        }
        public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
        }
        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }
        public String getPreviousHash() {
            return previousHash;
        }
        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
        public long getTimestamp() {
            return timestamp;
        }
        public byte[] getNonce() {
            return nonce;
        }
        public void setNonce(byte[] nonce) {
            this.nonce = nonce;
        }
    }

    public Transaction transactionList;
    public void setTranxLst(Transaction tranxLst) {
        this.transactionList = tranxLst;
    }

    @Override
    public String toString() {
        return "Block [blockHeader=" + blockHeader + ", transactionList=" + transactionList + "]";
    }
}
