package org.example;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    private static final SecureRandom random = new SecureRandom();

    // Генерация большого простого числа
    private static BigInteger generatePrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, random);
    }

    public static void main(String[] args) {
        // Шаг 1: Определяем публичные параметры p (простое число) и g (основа)
        int bitLength = 512; // Длина простого числа в битах
        BigInteger p = generatePrime(bitLength); // Простое число p
        BigInteger g = BigInteger.valueOf(2); // Основа g (может быть любым числом, но чаще используют маленькое)

        System.out.println("Public Parameters:");
        System.out.println("p = " + p);
        System.out.println("g = " + g);

        // Шаг 2: Генерация секретных ключей сторон (Alice и Bob)
        BigInteger a = new BigInteger(bitLength, random); // Секретный ключ Alice
        BigInteger b = new BigInteger(bitLength, random); // Секретный ключ Bob

        System.out.println("\nPrivate Keys:");
        System.out.println("Alice's private key (a) = " + a);
        System.out.println("Bob's private key (b) = " + b);

        // Шаг 3: Вычисление открытых ключей
        BigInteger A = g.modPow(a, p); // Открытый ключ Alice
        BigInteger B = g.modPow(b, p); // Открытый ключ Bob

        System.out.println("\nPublic Keys:");
        System.out.println("Alice's public key (A) = " + A);
        System.out.println("Bob's public key (B) = " + B);

        // Шаг 4: Вычисление общего секретного ключа
        BigInteger secretKeyAlice = B.modPow(a, p); // Общий ключ, вычисленный Alice
        BigInteger secretKeyBob = A.modPow(b, p);  // Общий ключ, вычисленный Bob

        System.out.println("\nShared Secret Keys:");
        System.out.println("Alice's computed secret key = " + secretKeyAlice);
        System.out.println("Bob's computed secret key = " + secretKeyBob);

        // Проверка равенства общего ключа
        if (secretKeyAlice.equals(secretKeyBob)) {
            System.out.println("\nDiffie-Hellman Key Exchange successful!");
        } else {
            System.out.println("\nSomething went wrong!");
        }
    }
}
