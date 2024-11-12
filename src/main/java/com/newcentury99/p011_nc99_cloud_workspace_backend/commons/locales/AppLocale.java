package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.locales;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryAPI;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@LibraryClass
@Getter
public enum AppLocale {
    en("en"),
    ko("ko"),
    ja("ja"),
    cn("zh-cn"),
    de("de"),
    fr("fr"),
    es("es"),
    pt("pt"),
    it("it"),
    ru("ru"),
    vi("vi");

    private final String value;
    AppLocale(String value) {
        this.value = value;
    }

    @LibraryAPI
    public static Locale getLanguageSupported(String localeStr) throws IllegalArgumentException {
        for (AppLocale locale : AppLocale.values()) {
            if (locale.getValue().equalsIgnoreCase(localeStr)) {
                return Locale.of(locale.getValue());
            }
        }
        throw new IllegalArgumentException("Locale not supported");
    }

    @LibraryAPI
    public static String[] getLanguageSupportedList() {
        return Arrays.stream(AppLocale.values()).map(AppLocale::getValue).toArray(String[]::new);
    }
}
