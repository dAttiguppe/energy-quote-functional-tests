package com.test.pojo;

public class RunTimeExpectedData {
    public String postCode;
    public String electricalSupplierName;
    public String electricityUsage;
    public String userBillPresent;
    public String sameEnergySupplier;
    public String gasUsage;
    public String electricitySupplier;
    public String projection;
    public String gasSupplierName;
    public boolean isOnlyGasSupplier;
    public boolean isOnlyElectricitySupplier;
    public String tariff;

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public boolean isOnlyGasSupplier() {
        return isOnlyGasSupplier;
    }

    public void setOnlyGasSupplier(boolean onlyGasSupplier) {
        isOnlyGasSupplier = onlyGasSupplier;
    }

    public boolean isOnlyElectricitySupplier() {
        return isOnlyElectricitySupplier;
    }

    public void setOnlyElectricitySupplier(boolean onlyElectricitySupplier) {
        isOnlyElectricitySupplier = onlyElectricitySupplier;
    }

    public String getSameEnergySupplier() {
        return sameEnergySupplier;
    }

    public void setSameEnergySupplier(String sameEnergySupplier) {
        this.sameEnergySupplier = sameEnergySupplier;
    }

    public String getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(String electricityUsage) {
        this.electricityUsage = electricityUsage;
    }

    public String getGasUsage() {
        return gasUsage;
    }

    public void setGasUsage(String gasUsage) {
        this.gasUsage = gasUsage;
    }

    public String getElectricalSupplierName() {
        return electricalSupplierName;
    }

    public void setElectricalSupplierName(String electricalSupplierName) {
        this.electricalSupplierName = electricalSupplierName;
    }

    public String getGasSupplierName() {
        return gasSupplierName;
    }

    public void setGasSupplierName(String gasSupplierName) {
        this.gasSupplierName = gasSupplierName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getElectricitySupplier() {
        return electricitySupplier;
    }

    public void setElectricitySupplier(String electricitySupplier) {
        this.electricitySupplier = electricitySupplier;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getUserBillPresent() {
        return userBillPresent;
    }

    public void setUserBillPresent(String userBillPresent) {
        this.userBillPresent = userBillPresent;
    }

}
