package com.example.springbootdemo2.entity;

public class Ipv4Packet {
    private String version;
    private String headerLength;
    private String typeOfService;
    private String totalLength;
    private String identifier;
    private String flags;
    private String fragmentOffset;
    private String ttl;
    private String protocol;
    private String headerChecksum;
    private String sourceAddresses;
    private String destinationAddresses;
    private String options;

    public Ipv4Packet(){
        this.version="1.0";
    }

    public Ipv4Packet(String version, String headerLength, String typeOfService, String totalLength, String identifier, String flags, String fragmentOffset, String ttl, String protocol, String headerChecksum, String sourceAddresses, String destinationAddresses, String options) {
        this.version = version;
        this.headerLength = headerLength;
        this.typeOfService = typeOfService;
        this.totalLength = totalLength;
        this.identifier = identifier;
        this.flags = flags;
        this.fragmentOffset = fragmentOffset;
        this.ttl = ttl;
        this.protocol = protocol;
        this.headerChecksum = headerChecksum;
        this.sourceAddresses = sourceAddresses;
        this.destinationAddresses = destinationAddresses;
        this.options = options;
    }

    @Override
    public String toString() {
        return "{" +
                "version='" + version + '\'' +
                ", headerLength='" + headerLength + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                ", totalLength='" + totalLength + '\'' +
                ", identifier='" + identifier + '\'' +
                ", flags='" + flags + '\'' +
                ", fragmentOffset='" + fragmentOffset + '\'' +
                ", ttl='" + ttl + '\'' +
                ", protocol='" + protocol + '\'' +
                ", headerChecksum='" + headerChecksum + '\'' +
                ", sourceAddresses='" + sourceAddresses + '\'' +
                ", destinationAddresses='" + destinationAddresses + '\'' +
                ", options='" + options + '\'' +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String headerLength() {
        return headerLength;
    }

    public void headerLength(String headerLength) {
        this.headerLength = headerLength;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getFragmentOffset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(String fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeaderChecksum() {
        return headerChecksum;
    }

    public void setHeaderChecksum(String headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public String getSourceAddresses() {
        return sourceAddresses;
    }

    public void setSourceAddresses(String sourceAddresses) {
        this.sourceAddresses = sourceAddresses;
    }

    public String getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(String destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
