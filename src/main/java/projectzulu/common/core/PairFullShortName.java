package projectzulu.common.core;


/**
 * For usage see: {@link Pair}
 */
public class PairFullShortName<K extends Comparable, V extends Comparable> implements Comparable<PairFullShortName<K,V>> {
        private final K fullName;
        private final V shortName;

        public static <K extends Comparable, V extends Comparable> PairFullShortName<K, V> createPair(K fullName, V shortName) {
                return new PairFullShortName<K, V>(fullName, shortName);
        }

        public PairFullShortName(K fullName, V shortName) {
                this.fullName = fullName;
                this.shortName = shortName;
        }

        public K getFullName() {
                return fullName;
        }

        public V getShortName() {
                return shortName;
        }
        
        @Override
        public boolean equals(Object object){
          if (! (object instanceof PairFullShortName)) { return false; }
          PairFullShortName pair = (PairFullShortName)object;
          return fullName.equals(pair.fullName) && shortName.equals(pair.shortName);
        } 

        @Override
        public int hashCode(){
           return 7 * fullName.hashCode() + 13 * shortName.hashCode();
        }

		@Override
		public int compareTo(PairFullShortName<K, V> otherPair) {
			if(otherPair.equals(this)){
				return 0;
			}else{
				return this.getShortName().compareTo(otherPair.getShortName());
			}
		}
}