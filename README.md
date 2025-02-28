# 🐠 Aquarium Simulation


## 📌 Loyihaning asosiy xususiyatlari

- **Baliqlarning yashash sikli**: Har bir baliq tug'iladi, umrini yashaydi va oxir-oqibat vafot etadi.
- **Avtomatik umr kamayishi**: Har bir baliqning umri har soniyada kamayib boradi.
- **Ko'payish**: Erkak baliq urg'ochisini topib, yangi baliqlar tug'ilishiga sabab bo'ladi.
- **Baliqlarning avtomatik harakati**: Har bir baliq alohida thread (oqim) sifatida ishlaydi.
- **Akvariumdagi baliqlar sonini boshqarish**: Baliqlar vafot etganda akvariumdan o'chiriladi.
- **Logging**: Baliqlarning barcha harakatlari log fayllarga yoziladi.

---

## 🛠 Texnologiyalar

- **Java 17**
- **Concurrency API (ScheduledExecutorService, AtomicBoolean, AtomicInteger)**
- **SLF4J (Logging)**

---

## 🚀 Ishga tushirish

### 1. Java o'rnatilganligini tekshiring:

Loyihani ishga tushirishdan oldin, kompyuteringizda Java 17 yoki undan yuqori versiya o'rnatilgan bo'lishi kerak.

```sh
java -version
```

### 2. Loyihani klonlash:

```sh
git clone https://github.com/salikhdev/aqua-simulator.git
cd aqua-simulation
```

### 3. Loyihani ishga tushirish:

```sh
mvn clean install
java -jar target/aquarium-simulation.jar
```

---

## 📌 Sinflar va metodlar


### **2️⃣ `Fish.java` - Baliqlarning asosiy xususiyatlari**

Bu class barcha baliqlarning umumiy xususiyatlarini o'z ichiga oladi.

**Metodlar:**

- `decreaseLifespan()` - Baliqning umrini kamaytiradi.
- `die()` - Baliq vafot etganida, akvariumdan o‘chiriladi.

---

### **3️⃣ `MaleFish.java` - Erkak baliq**

Erkak baliq urg‘ochisini topib, ko‘payishga harakat qiladi.

**Metodlar:**

- `run()` - Baliqning hayot siklini avtomatik bajarish.
- `findMate()` - Urg‘ochi baliqni topish va juftlashish.

---

### **4️⃣ `FemaleFish.java` - Urg‘ochi baliq**

Urg‘ochi baliq juftlashish uchun tayyor bo‘lganda, yangi baliq tug‘iladi.

**Metodlar:**

- `tryMate()` - Juftlashish imkoniyatini tekshiradi.
- `bornFish()` - Yangi baliq tug‘ilishi.

---

### **5️⃣ `Aquarium.java` - Baliqlar yashaydigan joy**

Bu class baliqlarning yashash muhitini boshqaradi.

**Metodlar:**

- `addFish(Fish fish)` - Baliq qo'shish.
- `removeFish(Fish fish)` - Baliqni olib tashlash.
- `stopSimulation()` - Akvariumdagi barcha jarayonlarni to‘xtatish.

---

### **6️⃣ `RandomUtil.java` - Tasodifiy sonlar generatsiyasi**

Bu yordamchi class baliqlar uchun tasodifiy umr va jins yaratadi.

**Metodlar:**

- `randomLifespan()` - Baliqning umrini tasodifiy belgilaydi.
- `randomGender()` - Baliq jinsini tasodifiy belgilaydi.

---

## 📌 Natijalar

Dastur ishga tushganda, quyidagi loglar hosil bo‘ladi:

```
🐠 Aquarium simulation started!
MALE fish 1 entered the aquarium.
FEMALE fish 2 entered the aquarium.
💑 1 and 2 are mating!
A new MALE fish (ID: 3) is born in the aquarium!
```

Agar barcha baliqlar vafot etsa, simulyatsiya avtomatik to‘xtaydi:

```
❌ Aquarium simulation stopped gracefully!
```

---

## 📞 Bog'lanish

Agar sizda loyiha bo‘yicha savollar bo‘lsa, quyidagi manzillar orqali bog‘lanishingiz mumkin:

- Email: salikhdev@gmail.com
- GitHub: [salikhdev](https://github.com/salikhdev)

---


