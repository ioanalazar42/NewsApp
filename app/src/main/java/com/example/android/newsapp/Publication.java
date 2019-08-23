package com.example.android.newsapp;

public class Publication {

    /** title of the publication */
    private String title;

    /** section publication belongs to */
    private String section;

    /** Website URL of the publication */
    private String url;

    /**
     * Constructs a new {@link Publication} object.
     *
     * @param requiredTitle is the title of the publication
     * @param reguiredSection is the section the publication belongs to
     * @param requiredUrl is the website URL to find more details about the publication
     */
    public Publication(String requiredTitle, String reguiredSection, String requiredUrl) {
        title = requiredTitle;
        section = reguiredSection;
        url = requiredUrl;
    }

    /**
     * Returns the title of the publication.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the section the publication belongs to.
     */
    public String getSection() { return section; }

    /**
     * Returns the website URL to find more information about the publication.
     */
    public String getUrl() {
        return url;
    }
}
