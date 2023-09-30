package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception
    {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance, 5000.0);

        this.tradeLicenseId=tradeLicenseId;

        if(balance<5000)
            throw new Exception("Insufficient Balance");

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if (!this.isLicenseNumberValid(this.tradeLicenseId))
        {
            String rearrangedId = this.rearrangeString(this.tradeLicenseId);
            if (rearrangedId == "")
            {
                throw new Exception("Valid License can not be generated");
            }

            this.tradeLicenseId = rearrangedId;
        }

    }

    public String getTradeLicenseId() {
        return this.tradeLicenseId;
    }


    public char getMaxCountChar(int[] count) {
        int max = 0;
        char ch = 0;

        for(int i = 0; i < 26; ++i) {
            if (count[i] > max) {
                max = count[i];
                ch = (char)(65 + i);
            }
        }

        return ch;
    }


    public String rearrangeString(String S) {
        int N = S.length();
        int[] count = new int[26];

        for(int i = 0; i < 26; ++i) {
            count[i] = 0;
        }

        char[] var9 = S.toCharArray();
        int maxCount = var9.length;

        int ind;
        for(int var6 = 0; var6 < maxCount; ++var6) {
            ind = var9[var6];
            ++count[ind - 65];
        }

        char ch_max = this.getMaxCountChar(count);
        maxCount = count[ch_max - 65];
        if (maxCount > (N + 1) / 2) {
            return "";
        } else {
            String res = "";

            for(ind = 0; ind < N; ++ind) {
                res = res + " ";
            }

            for(ind = 0; maxCount > 0; --maxCount) {
                res = res.substring(0, ind) + ch_max + res.substring(ind + 1);
                ind += 2;
            }

            count[ch_max - 65] = 0;

            for(int i = 0; i < 26; ++i) {
                while(count[i] > 0) {
                    ind = ind >= N ? 1 : ind;
                    String var10000 = res.substring(0, ind);
                    res = var10000 + (char)(65 + i) + res.substring(ind + 1);
                    ind += 2;
                    int var10002 = count[i]--;
                }
            }

            return res;
        }
    }

    public boolean isLicenseNumberValid(String licenseId) {
        for(int i = 0; i < licenseId.length() - 1; ++i) {
            if (licenseId.charAt(i) == licenseId.charAt(i + 1)) {
                return false;
            }
        }

        return true;
    }

}
