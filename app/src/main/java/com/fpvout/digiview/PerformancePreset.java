package com.fpvout.digiview;

import android.content.SharedPreferences;

public class PerformancePreset {
    int h264ReaderMaxSyncFrameSize = 131072;
    int h264ReaderSampleTime = 10000;
    int exoPlayerMinBufferMs = 500;
    int exoPlayerMaxBufferMs = 2000;
    int exoPlayerBufferForPlaybackMs = 17;
    int exoPlayerBufferForPlaybackAfterRebufferMs = 17;
    DataSourceType dataSourceType = DataSourceType.INPUT_STREAM;

    private PerformancePreset(){

    }

    private PerformancePreset(int mH264ReaderMaxSyncFrameSize, int mH264ReaderSampleTime, int mExoPlayerMinBufferMs, int mExoPlayerMaxBufferMs, int mExoPlayerBufferForPlaybackMs, int mExoPlayerBufferForPlaybackAfterRebufferMs, DataSourceType mDataSourceType){
        h264ReaderMaxSyncFrameSize = mH264ReaderMaxSyncFrameSize;
        h264ReaderSampleTime = mH264ReaderSampleTime;
        exoPlayerMinBufferMs = mExoPlayerMinBufferMs;
        exoPlayerMaxBufferMs = mExoPlayerMaxBufferMs;
        exoPlayerBufferForPlaybackMs = mExoPlayerBufferForPlaybackMs;
        exoPlayerBufferForPlaybackAfterRebufferMs = mExoPlayerBufferForPlaybackAfterRebufferMs;
        dataSourceType = mDataSourceType;
    }

    static PerformancePreset getPreset(PresetType p) {
        switch (p) {
            case CONSERVATIVE:
                return new PerformancePreset(131072, 14000, 500, 2000, 34, 34, DataSourceType.INPUT_STREAM);
            case AGGRESSIVE:
                return new PerformancePreset(131072, 7000, 50, 2000, 17, 17, DataSourceType.INPUT_STREAM);
            case LEGACY:
                return new PerformancePreset(30720, 200, 32768, 65536, 0, 0, DataSourceType.BUFFERED_INPUT_STREAM);
            case LEGACY_BUFFERED:
                return new PerformancePreset(30720, 300, 32768, 65536, 34, 34, DataSourceType.BUFFERED_INPUT_STREAM);
            case DEFAULT:
            default:
                return new PerformancePreset(131072, 10000, 500, 2000, 17, 17, DataSourceType.INPUT_STREAM);
        }
    }

    public enum DataSourceType {
        INPUT_STREAM,
        BUFFERED_INPUT_STREAM
    }

    static PerformancePreset getPreset(String p) {
        switch (p) {
            case "conservative":
                return getPreset(PresetType.CONSERVATIVE);
            case "aggressive":
                return getPreset(PresetType.AGGRESSIVE);
            case "legacy":
                return getPreset(PresetType.LEGACY);
            case "legacy_buffered":
                return getPreset(PresetType.LEGACY_BUFFERED);
            case "default":
            default:
                return getPreset(PresetType.DEFAULT);
        }
    }

    static int getPresetChoiceId(String p) {
        switch (p) {
            case "conservative":
                return R.id.preset_conservative;
            case "aggressive":
                return R.id.preset_aggressive;
            case "legacy":
                return R.id.preset_legacy;
            case "legacy_buffered":
                return R.id.preset_legacy_buffered;
            case "default":
            default:
                return R.id.preset_default;
        }
    }

    static void savePresetChoice(SharedPreferences sharedPreferences, String key, int choiceId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (choiceId) {
            case R.id.preset_conservative:
                editor.putString(key, "conservative");
                break;
            case R.id.preset_aggressive:
                editor.putString(key, "aggressive");
                break;
            case R.id.preset_legacy:
                editor.putString(key, "legacy");
                break;
            case R.id.preset_legacy_buffered:
                editor.putString(key, "legacy_buffered");
                break;
            case R.id.preset_default:
            default:
                editor.putString(key, "default");
        }
        editor.commit();
    }

    public enum PresetType {
        DEFAULT,
        CONSERVATIVE,
        AGGRESSIVE,
        LEGACY,
        LEGACY_BUFFERED
    }

    @Override
    public String toString() {
        return "PerformancePreset{" +
                "h264ReaderMaxSyncFrameSize=" + h264ReaderMaxSyncFrameSize +
                ", h264ReaderSampleTime=" + h264ReaderSampleTime +
                ", exoPlayerMinBufferMs=" + exoPlayerMinBufferMs +
                ", exoPlayerMaxBufferMs=" + exoPlayerMaxBufferMs +
                ", exoPlayerBufferForPlaybackMs=" + exoPlayerBufferForPlaybackMs +
                ", exoPlayerBufferForPlaybackAfterRebufferMs=" + exoPlayerBufferForPlaybackAfterRebufferMs +
                ", dataSourceType=" + dataSourceType +
                '}';
    }
}
