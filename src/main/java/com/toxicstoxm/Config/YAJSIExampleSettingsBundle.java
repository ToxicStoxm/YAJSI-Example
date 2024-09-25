package com.toxicstoxm.Config;

import com.toxicstoxm.YAJSI.api.settings.Setting;
import com.toxicstoxm.YAJSI.api.settings.SettingsBundle;
import com.toxicstoxm.YAJSI.api.settings.YAJSISetting;
import com.toxicstoxm.YAJSI.api.settings.YAMLSetting;
import lombok.Getter;

public class YAJSIExampleSettingsBundle implements SettingsBundle {

    @YAMLSetting(path = "YAJSI-Example.Greeting-Message")
    public static class GreetingMessage extends YAJSISetting<String> {
        @Getter
        public static GreetingMessage instance;

        public GreetingMessage(Setting<Object> setting) {
            super(setting, String.class);
            instance = this;
        }
    }

}
