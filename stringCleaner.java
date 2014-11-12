public class stringCleaner {
    private stringCleaner() {
    }

    public String cleanString(String toClean) {
        toClean = toClean.replace("\'", "");
        toClean = toClean.replace("[", "");
        toClean = toClean.replace("]", "");
        toClean = toClean.replace("\"", "");
        toClean = toClean.replace("\',\'", " ");
        toClean = "\'" + toClean + "\'";
        return toClean;
    }
}
