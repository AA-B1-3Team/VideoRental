public enum VideoType {
    VHS(1),
    CD(2),
    DVD(3);

    private final int value;

    VideoType(int value) {
        this.value = value;
    }

    public static VideoType convertFrom(int value) {
        for (VideoType videoType : VideoType.values()) {
            if (videoType.value == value) {
                return videoType;
            }
        }

        return null;
    }
}
