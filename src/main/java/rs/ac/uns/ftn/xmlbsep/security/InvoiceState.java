package rs.ac.uns.ftn.xmlbsep.security;

public enum InvoiceState {

    BOSS("boss"),
    DIRECTOR("director"),
    PARTNER("partner");

    private final String name;

    /**
     * @param name
     */
    private InvoiceState(final String name) {
        this.name = name;
    }

    public boolean equals(String otherName) {
        return (otherName != null) && name.equals(otherName);
    }

    public static InvoiceState factory(String name) {
        if ("BOSS".equals(name)) {
            return BOSS;
        }
        else if ("DIRECTOR".equals(name)) {
            return DIRECTOR;
        }
        else if ("PARTNER".equals(name)) {
            return PARTNER;
        }
        return null;
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }
}
