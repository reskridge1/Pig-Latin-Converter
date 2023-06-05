package converter;

import reskr.util.StringExtras;

/**
 *
 * @author Reskr
 */
public class Converter
{

  static String[] astrConsonantClusters =
  {
    "al", "bl", "cl", "fl", "gl", "pl", "sl", "wh",
    "th", "sh", "ch", "ph", "br", "cr", "dr", "fr",
    "gr", "pr", "tr", "sc", "sk", "sm", "sn", "sp",
    "st", "sw", "tw", "sch", "shr", "spl", "squ", "thr",
    "spr", "scr", "sph"
  };

  /**
   * converts Pig Latin for a string if the word starts with a consonant cluster
   * (e.g. "th", "br", etc.)
   *
   * @param str
   * @return
   */
  public static String getPigLatinForClusteredString(String str)
  {
    for(String cluster : astrConsonantClusters)
    {
      if(StringExtras.startsWith(str, cluster))
      {
        return str.substring(cluster.length()) + cluster + "ay";
      }
    }
    return str;
  }

  /**
   * converts a word to pig latin
   *
   * @param str
   * @return
   */
  public static String toPigLatin(String str)
  {
    StringBuilder sb = new StringBuilder();
    str = StringExtras.trim(str);

    if(!StringExtras.isBlank(str))
    {
      if(!StringExtras.equals(str, getPigLatinForClusteredString(str))) //handles if it starts with a consonant cluster
      {
        return getPigLatinForClusteredString(str);
      }
      if(StringExtras.equals(str, "I") || (StringExtras.equals(str, "a") || StringExtras.equals(str, "an"))) //handles articles "a" and "an", and the pronoun "I"
      {
        return str;
      }
      else
      {
        char chStart = str.charAt(0);
        char chLast = str.charAt(str.length() - 1);

        if(StringExtras.isVowel(chStart) && StringExtras.isVowel(chLast)) //checks if it starts and ends with vowel
        {
          sb.append(str);
          sb.append("way");
        }
        else if(StringExtras.startsWith(str, "h") && StringExtras.isVowel(str.charAt(1))) //if the word starts with h and is followed by vowel (treat it like the case for vowels)
        {
          sb.append(str);
          sb.append("way");
        }
        else if(StringExtras.isVowel(chStart) && !StringExtras.isVowel(chLast)) //case where it starts with vowel but doesn't end with one
        {
          sb.append(str);
          sb.append("ay");
        }
        else //regular case where it takes first char, moves it to the end, and add "ay"
        {
          sb.append(str.substring(1));
          sb.append(chStart);
          sb.append("ay");
        }
      }
    }
    return sb.toString();
  }

  /**
   * converts a sentence to pig latin
   *
   * @param str
   * @return
   */
  public static String sentenceToPigLatin(String str)
  {
    StringBuilder sb = new StringBuilder();
    if(str != null)
    {
      java.util.List<String> alWords = StringExtras.tokenizeStringToList(str, StringExtras.SINGLE_SPACE);
      if(alWords == null)
      {
        return StringExtras.EMPTY_STRING;
      }
      if(!alWords.isEmpty())
      {
        for(String strWord : alWords)
        {
          sb.append(toPigLatin(strWord));
          sb.append(StringExtras.SINGLE_SPACE);
        }
      }
    }
    return sb.toString();
  }
}
