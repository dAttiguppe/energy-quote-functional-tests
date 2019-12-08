package com.test.enums;

public enum EnergySelectionEnum {
    GAS("Gas"), ELECTRICITY("Electricity"), GASANDELECTRICITY("Gas&Electricity");

    private String energySelection;

     EnergySelectionEnum(String energySelection) {
        this.energySelection = energySelection;
    }

    public String getEnergySelctionOption() {
        return energySelection;
    }

    public static  EnergySelectionEnum findByCode(String energySelection) {
        for ( EnergySelectionEnum option :  EnergySelectionEnum.values()) {
            if (option.getEnergySelctionOption().equalsIgnoreCase(energySelection)) {
                return option;
            }
        }
        return null;
    }
}