package com.fa.imaged.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.fa.imaged.activity.HomeActivity;
import com.fa.imaged.R;

public class SettingsFragment extends PreferenceFragment {
    public SettingsFragment() {}
	// Theme Info
	public static int selectedThemeInt;
	public static String selectedTheme;
	public static String[] themePrimaryColors;
	public static String[] themePrimaryDarkColors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource

        addPreferencesFromResource(R.xml.prefs);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        prefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
        	@Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                selectedTheme = prefs.getString("theme_color", "0");
                selectedThemeInt = Integer.parseInt(selectedTheme);
                HomeActivity.updateTheme(selectedThemeInt);
                FeedFragment2.updateFab(selectedThemeInt);
            }
        });
    }
}