# Caesar cipher.
echo "CAESAR" | tr "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "DEFGHIJKLMNOPQRSTUVWXYZABC"

# Case-insensitive Caesar cipher.
echo "Klartext" | tr "abcdefghijklmnopqrstuvwxyz" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" | tr "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "DEFGHIJKLMNOPQRSTUVWXYZABC"

# ROT-13 Verschlüsselung.
echo "Klartext" | tr "abcdefghijklmnopqrstuvwxyz" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" | tr "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "NOPQRSTUVWXYZABCDEFGHIJKLM"
# Gleicher Aufruf mit verschlüsseltem Text.
echo "XYNEGRKG" | tr "abcdefghijklmnopqrstuvwxyz" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" | tr "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "NOPQRSTUVWXYZABCDEFGHIJKLM"

# Was fällt auf? Das Alphabet enthält 26 Buchstaben. "Rotiert" man zweimal 13 Buchstaben,
# so kommt man am Ende wieder bei dem gleichen Alphabet an.


