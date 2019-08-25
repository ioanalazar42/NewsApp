package com.example.android.newsapp;

public class Publication {

    /** title of the publication */
    private String title;

    /** author of the publication */
    private String author;

    /** section publication belongs to */
    private String section;

    /** Website URL of the publication */
    private String url;


    /** Date of the publication */
    private String date;

    /**
     * Constructs a new {@link Publication} object.
     *
     * @param requiredTitle is the title of the publication
     * @param reguiredSection is the section the publication belongs to
     * @param requiredUrl is the website URL to find more details about the publication
     * @param requiredDate is the date the article was published
     */
    public Publication(String requiredTitle, String reguiredSection,
                       String requiredUrl, String requiredDate, String requiredAuthor) {
        title = requiredTitle;
        section = reguiredSection;
        url = requiredUrl;
        date = requiredDate;
        author = requiredAuthor;
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

    /**
     * Returns the date the article was published.
     */
    public String getDate() { return date; }

    /**
     * Returns the author of the article
     */
    public String getAuthor() { return author; }
}
