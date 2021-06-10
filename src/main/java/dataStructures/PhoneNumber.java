package dataStructures;

import java.util.Objects;

public class PhoneNumber implements Comparable<PhoneNumber> {
        private String areaCode;
        private String prefix;
        private String number;
        private String phoneNumber;

        public PhoneNumber(String n){
                       verify(n);
                       String[] str = n.split("-");
                               this.areaCode = str[0];
                               this.prefix = str[1];
                               this.number = str[2];
                               this.phoneNumber = areaCode+prefix+number;
        }

        public int compareTo(PhoneNumber n){
                int num = this.phoneNumber.compareTo(n.phoneNumber);
                return num == 0 ? this.phoneNumber.compareTo(n.phoneNumber) : 0;
        }

        private void verify(String n){
                if (!n.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
                        throw new IllegalArgumentException("Not a valid number");
                }
        }

        private int hashCode (PhoneNumber key) {
                return Objects.hashCode(key);
        }

        public String getAreaCode() {
                return areaCode;
        }

        public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
        }

        public String getPrefix() {
                return prefix;
        }

        public void setPrefix(String prefix) {
                this.prefix = prefix;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;

        }

        @Override
        public String toString() {
                return "PhoneNumber{" +
                        "areaCode='" + areaCode + '\'' +
                        ", prefix='" + prefix + '\'' +
                        ", number='" + number + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        '}';
        }
}

