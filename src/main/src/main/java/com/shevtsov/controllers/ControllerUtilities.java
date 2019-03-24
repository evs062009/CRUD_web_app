package com.shevtsov.controllers;

import org.springframework.ui.ModelMap;

class ControllerUtilities {
    static String invalidParamMsg = "Invalid parameter!!!";


    static String printMsg(ModelMap modelMap, Object message, String jspName) {
        modelMap.put("message", message);
        return jspName;
    }
}
