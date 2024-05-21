import java.util.HashMap;
import java.util.Map;
class NumberDic implements DicInterface {
    private Map<String, String> dictionary;
    private String regex;
    private String filePath;

    public NumberDic(String filePath) {
        dictionary = new HashMap<>();
        regex = "^[0-9]{5}$";
        this.filePath = filePath;
        Method.readFromFile(dictionary, filePath, regex);
    }
    @Override
    public String getFilePath() {
        return filePath;
    }
    @Override
    public boolean addEntry(String key, String value) {
        boolean result = Method.addEntry(dictionary, key, value, regex);
        if (result) {
            Method.writeToFile(dictionary, filePath);
        }
        return result;
    }

    @Override
    public boolean deleteByKey(String key) {
        boolean result = Method.deleteByKey(dictionary, key);
        if (result) {
            Method.writeToFile(dictionary, filePath);
        }
        return result;
    }

    @Override
    public String findByKey(String key) {
        return Method.findByKey(dictionary, key);
    }

    @Override
    public Map<String, String> getDictionary() {
        return new HashMap<>(dictionary);
    }

    @Override
    public void writeToFile() {
        Method.writeToFile(dictionary, filePath);
    }
}