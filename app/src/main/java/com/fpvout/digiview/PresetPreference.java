package com.fpvout.digiview;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceViewHolder;

public class PresetPreference extends Preference {
    public PresetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.setWidgetLayoutResource(R.layout.video_preset_choice);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        final String VideoPreset = "VideoPreset";
        RadioGroup mVideoPressRadioGroup =  (RadioGroup) holder.findViewById(R.id.radioGroup);
        Log.i("jones", "mVideoPressRadioGroup = " + mVideoPressRadioGroup);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String strVideoPreset = sharedPreferences.getString(VideoPreset, "default");
        Log.i("jones", "strVideoPreset = " + strVideoPreset);
        Log.i("jones", "mVideoPressRadioGroup = " + mVideoPressRadioGroup);
        Log.i("jones", "mVideoPressRadioGroup check id = " + PerformancePreset.getPresetChoiceId(strVideoPreset));
        mVideoPressRadioGroup.check(PerformancePreset.getPresetChoiceId(strVideoPreset));
        mVideoPressRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                PerformancePreset.savePresetChoice(sharedPreferences, VideoPreset, checkedId);
            }
        });
    }
}
