package com.test.elements.detailsPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.test.testutils.SeleniumDriverHelper.clickElementUsingJs;
import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

public class TariffTypeSection {

    public String fixedTariffLocator = "#tariff-selection-question > div > label.fixed-rate > span.radio-button-text >span";
    public String variableTariffLocator ="#tariff-selection-question > div > label.variable-rate.checked > span.radio-button-text > span";
    public String allTariffLocator = "#tariff-selection-question > div > label.both-rate > span.radio-button-text > span";

    private static final Logger logger = LoggerFactory.getLogger(TariffTypeSection.class);

    public void chooseTariff(String tariff){
        switch(tariff){
            case "FixedTariff":{
                 clickElementUsingJs(findElementByCss(fixedTariffLocator));
                break;
            }
            case "VariableTariff":{
                 clickElementUsingJs(findElementByCss(variableTariffLocator));
                break;
            }
            case "AllTarriff":{
                 clickElementUsingJs(findElementByCss(allTariffLocator));
                break;
            }
            default:
                logger.info("Not a vaild tariff selection");
        }
    }

}
