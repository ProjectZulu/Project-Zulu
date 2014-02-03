package projectzulu.common.core;

/**
 * For usage see: {@link Pair}
 */
public class PairDirectoryFile<K, V> {
        private final K directory;
        private final V file;

        public static <K, V> PairDirectoryFile<K, V> createPair(K directory, V file) {
                return new PairDirectoryFile<K, V>(directory, file);
        }

        public PairDirectoryFile(K directory, V file) {
                this.file = file;
                this.directory = directory;
        }

        public K getDirectory() {
                return directory;
        }

        public V getFile() {
                return file;
        }
        
        @Override
        public boolean equals(Object object){
          if (! (object instanceof PairDirectoryFile)) { return false; }
          PairDirectoryFile pair = (PairDirectoryFile)object;
          return directory.equals(pair.directory) && file.equals(pair.file);
        } 

        @Override
        public int hashCode(){
           return 7 * directory.hashCode() + 13 * file.hashCode();
        }
}