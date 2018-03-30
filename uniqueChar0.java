class Unique {
  public static boolean isUnique(String s) {
    boolean ascii_set = new boolean[256];
    for (int i = 0, len = s.length(); i < len; i++) {
      if (ascii_set[s.charAt(i)])
        return false;
      else
        ascii_set[s.charAt(i)] = true;
    }
    return true;
  }
}
