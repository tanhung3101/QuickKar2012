/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarModel.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class QuickKarModel {

    private static QuickKarModel model = new QuickKarModel();

    /* accountMap
     * Key = username; Value = AccountInfo
     *
     * songMap
     * Key = song code; Value = SongInfo
     *
     * tagMap
     * Key = tagged word; Value = Set of song codes that contain tagged word
     *
     * tagMapWithoutAccent
     * Key = tagged word without accent; Value = set of referenced words to the tagged word without accent
     *
     * rateMap
     * Key = song code; Value = Rate
     *
     * staffAndCustomerRateMap
     * Key = staff's username or customer's telephone number; Value = Another Map
     * that
     * Key = song code; Value = SongRateModel
     *
     * favoriteSongMap
     * Key = username; Value = a set of song codes
     */
    private Map<String, AccountInfo> accountMap = new LinkedHashMap<String, AccountInfo>();
    private Map<String, SongInfo> songMap = new HashMap<String, SongInfo>();
    private Map<String, HashSet<String>> tagMap = new HashMap<String, HashSet<String>>();
    private Map<String, HashSet<String>> tagMapWithoutAccent = new HashMap<String, HashSet<String>>();
    private Map<String, RateModel> rateMap = new HashMap<String, RateModel>();
    private Map<String, HashMap<String, SongRateModel>> staffAndCustomerRateMap = new HashMap<String, HashMap<String, SongRateModel>>();
    private Map<String, HashSet<String>> favoriteSongMap = new HashMap<String, HashSet<String>>();
    private ResourceBundle resources = ResourceBundle.getBundle("QuickKarProperties/quickkar_EN");
    private boolean isEng = true;

    public boolean isIsEng() {
        return isEng;
    }

    private QuickKarModel() {

    }

    public static QuickKarModel getModel() {
        return model;
    }

    public void setReSource(ResourceBundle resource){
        this.resources = resource;
    }

    public ResourceBundle getResource(){
        return this.resources;
    }

        public ResourceBundle switchReSource(){
          //  System.out.println("isENG:"+isEng);
            if(isEng){

        isEng = false;
        resources = ResourceBundle.getBundle("QuickKarProperties/quickkar_VN");
            }else{
                isEng=true;

        resources = ResourceBundle.getBundle("QuickKarProperties/quickkar_EN");
            }
            //System.out.println("isENG:"+isEng);
            return resources;
    }

    public Map<String, AccountInfo> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, AccountInfo> accountMap) {
        this.accountMap = accountMap;
    }

    public Map<String, SongInfo> getSongMap() {
        return songMap;
    }

    public void setSongMap(Map<String, SongInfo> songMap) {
        this.songMap = songMap;
    }

    public Map<String, HashSet<String>> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, HashSet<String>> tagMap) {
        this.tagMap = tagMap;
    }

    public Map<String, HashSet<String>> getTagMapWithoutAccent() {
        return tagMapWithoutAccent;
    }

    public void setTagMapWithoutAccent(Map<String, HashSet<String>> tagMapWithoutAccent) {
        this.tagMapWithoutAccent = tagMapWithoutAccent;
    }

    public Map<String, RateModel> getRateMap() {
        return rateMap;
    }

    public void setRateMap(Map<String, RateModel> rateMap) {
        this.rateMap = rateMap;
    }

    public Map<String, HashMap<String, SongRateModel>> getStaffAndCustomerRateMap() {
        return staffAndCustomerRateMap;
    }

    public void setStaffAndCustomerRateMap(Map<String, HashMap<String, SongRateModel>> staffAndCustomerRateMap) {
        this.staffAndCustomerRateMap = staffAndCustomerRateMap;
    }

    public Map<String, HashSet<String>> getFavoriteSongMap() {
        return favoriteSongMap;
    }

    public void setFavoriteSongMap(Map<String, HashSet<String>> favoriteSongMap) {
        this.favoriteSongMap = favoriteSongMap;
    }

    public void deleteAllSong() {
        songMap.clear();
        tagMap.clear();
        tagMapWithoutAccent.clear();
        rateMap.clear();
        staffAndCustomerRateMap.clear();
        favoriteSongMap.clear();
    }
    /*
     * Rate process
     */

    private void addRate(String songCode, int star) {

        //add rate to map or create new rate
        RateModel rate = rateMap.get(songCode);
        if (rate != null) {
            rate.increaseStar(star);
        } else {
            RateModel newRate = new RateModel();
            newRate.increaseStar(star);
            rateMap.put(songCode, newRate);
        }
    }

    private void deleteRate(String songCode, int star) {

        //delete rate to map or decrease rate
        RateModel rate = rateMap.get(songCode);
        if (rate != null) {
            rate.decreaseStar(star);
            if (rate.getCount() == 0) {
                rateMap.remove(songCode);
            }
        }
    }

    public int getSongRateCount(String songCode) {

        RateModel rateModel = rateMap.get(songCode);

        if (rateModel != null) {
            return rateModel.getCount();
        } else {
            return 0;
        }
    }

    public Set<Map.Entry<String, Double>> getTopRate() {

        SortedSet<Map.Entry<String, RateModel>> sortedEntries = new TreeSet<Map.Entry<String, RateModel>>(new Comparator<Map.Entry<String, RateModel>>() {

            public int compare(Map.Entry<String, RateModel> o1, Map.Entry<String, RateModel> o2) {
                return (o1.getValue().averageRate() < o2.getValue().averageRate()) ? 1 : -1;
            }
        });
        sortedEntries.addAll(rateMap.entrySet());

        Set<Map.Entry<String, Double>> returnSet = new LinkedHashSet<Map.Entry<String, Double>>();
        int count = 0;
        for (Map.Entry<String, RateModel> entry : sortedEntries) {
            returnSet.add(new MapEntryElement<String, Double>(entry.getKey(), entry.getValue().averageRate()));
            count++;
            if (count == 10) {
                break;
            }
        }
        return returnSet;
    }

    /*
     * Specifically rate for customer and staff
     */
    public void staffAndCustomerRating(String username, String songCode, int ratingStar) {

        Map<String, SongRateModel> specificStaffRateMap = staffAndCustomerRateMap.get(username);

        if (ratingStar != 0) {
            if (specificStaffRateMap != null) {
                if (specificStaffRateMap.containsKey(songCode)) {
                    SongRateModel songRateModel = specificStaffRateMap.get(songCode);
                    deleteRate(songCode, songRateModel.getRatingStar());
                    songRateModel.setRatingStar(ratingStar);
                } else {
                    specificStaffRateMap.put(songCode, new SongRateModel(ratingStar));
                }
            } else {
                HashMap<String, SongRateModel> newUserMap = new HashMap<String, SongRateModel>();
                newUserMap.put(songCode, new SongRateModel(ratingStar));
                staffAndCustomerRateMap.put(username, newUserMap);
            }
            addRate(songCode, ratingStar);
        } else {
            if (specificStaffRateMap != null) {
                SongRateModel songRateModel = specificStaffRateMap.get(songCode);
                if (songRateModel != null) {
                    System.out.println("Test: " + songRateModel.getRatingStar());
                    deleteRate(songCode, songRateModel.getRatingStar());
                    specificStaffRateMap.remove(songCode);
                    if (specificStaffRateMap.isEmpty()) {
                        staffAndCustomerRateMap.remove(username);
                    }
                }
            }
        }
    }

    private void deleteSongRate(String songCode) {

        Set<Map.Entry<String, HashMap<String, SongRateModel>>> entrySet = staffAndCustomerRateMap.entrySet();
        for (Map.Entry<String, HashMap<String, SongRateModel>> entry : entrySet) {
            HashMap<String, SongRateModel> entryMap = entry.getValue();
            entryMap.remove(songCode);
            if (entryMap.isEmpty()) {
                staffAndCustomerRateMap.remove(entry.getKey());
            }
        }
        rateMap.remove(songCode);
    }

    public int getStar(String username, String songCode) {

        Map<String, SongRateModel> songRateMap = staffAndCustomerRateMap.get(username);
        if (songRateMap != null) {
            SongRateModel songRateModel = songRateMap.get(songCode);
            if (songRateModel != null) {
                return songRateModel.getRatingStar();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /*
     * favoriteList process
     */
    public void addFavoriteSong(String username, String songCode) {

        Set<String> favoriteSongSet = favoriteSongMap.get(username);

        if (favoriteSongSet != null) {
            favoriteSongSet.add(songCode);
        } else {
            favoriteSongMap.put(username, new HashSet<String>(Arrays.asList(new String[]{songCode})));
        }
    }

    public void removeFavoriteSong(String username, String songCode) {

        Set<String> favoriteSongSet = favoriteSongMap.get(username);

        if (favoriteSongSet != null) {
            favoriteSongSet.remove(songCode);
        }
    }

    private void deleteFavoriteSong(String songCode) {
        for (Map.Entry<String, HashSet<String>> entry : favoriteSongMap.entrySet()) {
            entry.getValue().remove(songCode);
        }
    }

    public List<String> getFavoriteSongCodeList(String username) {

        Set<String> favoriteSongSet = favoriteSongMap.get(username);
        if (favoriteSongSet != null) {
            List<String> favoriteSongList = new ArrayList<String>(favoriteSongSet);
            Collections.sort(favoriteSongList);
            return favoriteSongList;
        } else {
            return new ArrayList<String>();
        }
    }

    /*
     * Song process
     * In this section, it splits a song name, song lyric and song composer
     */
    private void addTag(String[] tag, String songCode) {

        //add tag for key word, put it into a set
        String[] tagAfterRemovingAccent = removeAccentFrom(tag);

        for (int i = 0; i < tag.length; i++) {

            //add song code to tag map
            if (tagMap.containsKey(tag[i])) {
                tagMap.get(tag[i]).add(songCode);
            } else {
                tagMap.put(tag[i], new HashSet<String>(Arrays.asList(new String[]{songCode})));
            }

            //add reference to tag map in tagMapWithoutAccent
            if (tagMapWithoutAccent.containsKey(tagAfterRemovingAccent[i])) {
                tagMapWithoutAccent.get(tagAfterRemovingAccent[i]).add(tag[i]);
            } else {
                tagMapWithoutAccent.put(tagAfterRemovingAccent[i], new HashSet<String>(Arrays.asList(tag[i])));
            }
        }
    }

    private void deleteTag(String[] tag, String songCode) {

        //remove song code tag
        String[] tagAfterRemovingAccent = removeAccentFrom(tag);

        for (int i = 0; i < tag.length; i++) {
            Set<String> set = tagMap.get(tag[i]);
            set.remove(songCode);
            if (set.isEmpty()) {
                tagMap.remove(tag[i]);
                Set<String> referenceSet = tagMapWithoutAccent.get(tagAfterRemovingAccent[i]);
                referenceSet.remove(tag[i]);
                if (referenceSet.isEmpty()) {
                    tagMapWithoutAccent.remove(tagAfterRemovingAccent[i]);
                }
            }
        }
    }

    private String[] getTag(String songName, String lyric, String composer) {

        //process to get song tag by the song name
        Set<String> set = new HashSet<String>();

        songName = songName.toLowerCase();
        lyric = lyric.toLowerCase();
        composer = composer.toLowerCase();

        set.addAll(Arrays.asList(songName.split("\\s")));
        set.addAll(Arrays.asList(lyric.split("\\s")));
        set.addAll(Arrays.asList(composer.split("\\s")));

        String[] tag = new String[set.size()];
        set.toArray(tag);

        return tag;
    }

    private String[] removeAccentFrom(String[] tag) {

        String[] tagAfterRemovingAccent = new String[tag.length];

        for (int i = 0; i < tag.length; i++) {
            tagAfterRemovingAccent[i] = QuickKarMethod.stringProcess(tag[i], true);
        }
        return tagAfterRemovingAccent;
    }

    public void addSongMap(String songCode, String songName, String lyrics, String composer) {
        songMap.put(songCode, new SongInfo(songName, lyrics, composer));
        addTag(getTag(songName, lyrics, composer), songCode);
    }

    public void deleteSongMap(String songCode) {
        SongInfo songInfo = songMap.get(songCode);
        deleteTag(getTag(songInfo.getName(), songInfo.getLyric(), songInfo.getComposer()), songCode);
        songMap.remove(songCode);
        deleteSongRate(songCode);
        deleteFavoriteSong(songCode);
    }

    public void editSongMap(String oldSongCode, String newSongCode, String newSongName, String newLyric, String newComposer) {

        SongInfo oldSong = songMap.get(oldSongCode);
        //remove old tag
        deleteTag(getTag(oldSong.getName(), oldSong.getLyric(), oldSong.getComposer()), oldSongCode);
        //add new tag
        addTag(getTag(newSongName, newLyric, newComposer), newSongCode);
        songMap.remove(oldSongCode);
        deleteSongRate(oldSongCode);
        songMap.put(newSongCode, new SongInfo(newSongName, newLyric, newComposer));
    }

    public void addAccount(String username, String password, String name, String email, String telephone, String otherInfo) {
        accountMap.put(username, new AccountInfo(password, name, email, telephone, otherInfo));
    }

    public void editAccount(String oldUsername, String newUsername, String password, String name, String email, String telephone, String otherInfo) {
        accountMap.remove(oldUsername);
        accountMap.put(newUsername, new AccountInfo(password, name, email, telephone, otherInfo));
    }

    public void deleteAccount(String username) {
        accountMap.remove(username);
    }

    //searching algorithm
    private Integer[] getKeyCodeFromTag(String[] tag) {

        //get key code from tag
        Set<Integer> keyCodeArray = new HashSet<Integer>();
        for (int index = 0; index < tag.length; index++) {
            try {
                Integer.parseInt(tag[index]);
                keyCodeArray.add(index);
            } catch (NumberFormatException ex) {
                //do nothing
            }
        }
        Integer[] returnKeyCodeArray = new Integer[keyCodeArray.size()];
        keyCodeArray.toArray(returnKeyCodeArray);

        return returnKeyCodeArray;
    }

    /*
     * simple search algorithm will search the tagMap and tagMapWithutAccent to
     * get the expected result
     */
    public Set<String> simpleSearch(String keywords, boolean anyOfTheKeyWords, boolean noneOfTheKeyWords) {

        Set<Map.Entry<String, HashSet<String>>> tagMapEntrySet = tagMap.entrySet();
        Set<Map.Entry<String, HashSet<String>>> tagMapWithouAccentEntrySet = tagMapWithoutAccent.entrySet();
        Set<String> result = new HashSet<String>();
        List<Set<String>> tagSet = new ArrayList<Set<String>>();
        String[] tag = QuickKarMethod.removeDuplicateWords(keywords);

        //initial tag set
        for (int i = 0; i < tag.length; i++) {
            tagSet.add(new HashSet<String>());
        }

        //get set of all tags of the keywords
        for (Map.Entry<String, HashSet<String>> entry : tagMapEntrySet) {

            String key = entry.getKey();
            Set<String> value = entry.getValue();

            for (int i = 0; i < tag.length; i++) {
                if (key.indexOf(tag[i]) > -1) {
                    tagSet.get(i).addAll(value);
                }
            }
        }

        //search tagMapWithoutAccent

        for (Map.Entry<String, HashSet<String>> entry : tagMapWithouAccentEntrySet) {

            String key = entry.getKey();
            Set<String> value = entry.getValue();

            for (int i = 0; i < tag.length; i++) {
                if (key.indexOf(tag[i]) > -1) {
                    for (String referenceEntry : value) {
                        if (!referenceEntry.equals(tag[i])) {
                            tagSet.get(i).addAll(tagMap.get(referenceEntry));
                        }
                    }
                }
            }
        }

        //search code

        Integer[] codeArray = getKeyCodeFromTag(tag);
        if (codeArray.length > 0) {

            Set<Map.Entry<String, SongInfo>> songMapEntrySet = songMap.entrySet();

            for (Map.Entry<String, SongInfo> entry : songMapEntrySet) {

                String key = entry.getKey();

                for (int i = 0; i < codeArray.length; i++) {
                    if (key.indexOf(tag[codeArray[i]]) > -1) {
                        tagSet.get(codeArray[i]).add(key);
                    }
                }
            }
        }
        //retain set process
        for (int i = 0; i < tagSet.size(); i++) {
            if (result.isEmpty() || anyOfTheKeyWords || noneOfTheKeyWords) {
                result.addAll(tagSet.get(i));
            } else {
                result.retainAll(tagSet.get(i));
            }
            if (result.isEmpty() && !anyOfTheKeyWords) {
                break;
            }
        }
        return result;
    }

    /*
     * simple string matching method
     * It returns true if the given string matches with its criteria
     */
    private boolean findMatchWordsForTheGivenString(String keyword, String value) {

        keyword = keyword.toLowerCase();
        value = value.toLowerCase();
        return keyword.indexOf(value) > -1 || QuickKarMethod.stringProcess(keyword, true).indexOf(value) > -1;
    }

    /*
     * findExactlyWords method
     * It finds against those exactKeyWordsArray again to find exactly search
     */
    private Set<String> findExactlyWords(Set<String> simpleSearchResult, String exactKeyWords) {

        Set<String> result = new HashSet<String>();

        for (String entry : simpleSearchResult) {

            SongInfo songInfo = songMap.get(entry);
            String songCode = entry;
            String songName = songInfo.getName();
            String songLyric = songInfo.getLyric();
            String songComposer = songInfo.getComposer();

            String key = exactKeyWords;
            if (findMatchWordsForTheGivenString(songName, key) || findMatchWordsForTheGivenString(songLyric, key)
                    || findMatchWordsForTheGivenString(songComposer, key) || songCode.indexOf(key) > -1) {
                result.add(songCode);
            }
        }
        return result;
    }

    /*
     * Advance search algorithm
     * First you get the words in double quote
     * It stores those exact match words in exactKeyWordsSet
     * tempKeyWords is the given keywords after removing double quote
     * if user uses double to find exact word match it will be process
     */
    public List<String> advanceSearch(String keyWords, boolean anyOfTheKeyWords, boolean noneOfTheKeyWords) {

        Set<String> exactKeyWordsSet = new HashSet<String>();
        String tempKeyWords = keyWords.replaceAll("\"", "").trim();
        String nonExactKeyWords = "";
        int nextPosition = 0;

        //get exact words been searched. It return exactKeywordsList and tempKeyWords
        while (nextPosition != -1) {
            int first = keyWords.indexOf("\"", nextPosition);
            int last = keyWords.indexOf("\"", first + 1);
            if (first != - 1 && last != -1) {
                String exactWords = keyWords.substring(first + 1, last).trim();
                String tempNonExactKeyWords = keyWords.substring(nextPosition, first).trim();
                if (!tempNonExactKeyWords.isEmpty()) {
                    nonExactKeyWords += tempNonExactKeyWords + " ";
                }
                if (!exactWords.isEmpty()) {
                    if (exactWords.indexOf(" ") > -1) {
                        exactKeyWordsSet.add(exactWords);
                    } else {
                        nonExactKeyWords += exactWords;
                    }
                }
                nextPosition = last + 1;
            } else {
                nonExactKeyWords += keyWords.substring(nextPosition).trim();
                nextPosition = -1;
            }
        }

        nonExactKeyWords = nonExactKeyWords.replaceAll("\"", "").trim();
        exactKeyWordsSet.remove("");

        Set<String> result = null;

        if (exactKeyWordsSet.size() > 0) {
            //advance search
            exactKeyWordsSet.remove("");
            for (String entry : exactKeyWordsSet) {
                Set<String> simpleSearchResult = simpleSearch(entry, anyOfTheKeyWords, noneOfTheKeyWords);
                if (result == null) {
                    result = new HashSet<String>(findExactlyWords(simpleSearchResult, entry));
                } else {
                    if (anyOfTheKeyWords || noneOfTheKeyWords) {
                        result.addAll(findExactlyWords(simpleSearchResult, entry));
                    } else {
                        result.retainAll(findExactlyWords(simpleSearchResult, entry));
                    }
                }
            }
            if (!nonExactKeyWords.isEmpty()) {
                Set<String> searchResultForNonExactKeyWords = simpleSearch(nonExactKeyWords, anyOfTheKeyWords, noneOfTheKeyWords);

                if (anyOfTheKeyWords || noneOfTheKeyWords) {
                    result.addAll(searchResultForNonExactKeyWords);
                } else {
                    result.retainAll(searchResultForNonExactKeyWords);
                }
            }
        } else {
            //simple search
            result = simpleSearch(tempKeyWords, anyOfTheKeyWords, noneOfTheKeyWords);
        }
        if (noneOfTheKeyWords) {
            Set<String> noneOfTheKeyWordsResult = new HashSet<String>(songMap.keySet());
            noneOfTheKeyWordsResult.removeAll(result);
            return new ArrayList<String>(noneOfTheKeyWordsResult);
        } else {
            return new ArrayList<String>(result);
        }
    }

    public boolean loginFunction(String username, String password) {

        if (accountMap.containsKey(username)) {
            if (accountMap.get(username).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
