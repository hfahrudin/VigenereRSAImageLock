# VigenèreRSAImageLock

<p align="center">
  <img width="260" height="150" src="https://user-images.githubusercontent.com/25025173/70690932-64f62780-1cea-11ea-8fc7-0926fb6398b3.PNG">
</p>

Secure your images with cryptographic algorithms. This application leverages **RSA** and **Vigenère encryption** to provide a robust solution for protecting image data. Inspired by a past project and college coursework, this tool combines classic and modern encryption techniques for educational and practical purposes.

---

## Features
- **Encryption**: Converts image data into secure, unreadable formats using **RSA** and **Vigenère encryption**.
- **Decryption**: Restores encrypted images back to their original form.
- **Educational Focus**: Learn how cryptographic algorithms work by exploring their implementation in this project.

---

## Demo

### **Encryption Process**
<p align="center">
  <img width="650" height="400" src="https://user-images.githubusercontent.com/25025173/70690915-57d93880-1cea-11ea-9fd2-b802db49db97.gif">
</p>

### **Decryption Process**
<p align="center">
  <img width="650" height="400" src="https://user-images.githubusercontent.com/25025173/70690896-50b22a80-1cea-11ea-9848-5b600e7c2c39.gif">
</p>

---

## Installation and Setup

1. **Clone the repository**:

2. **Open the project in NetBeans**:
   - Launch **NetBeans** and open the project by selecting `File` -> `Open Project`.
   - Navigate to the project folder and select the `image_encrypt_decrypt` folder to open it.

3. **Build the Project**:
   - In NetBeans, right-click on the project and select **Build** to compile the project.

---

## Usage

1. **Run the Application**:
   - After building the project in NetBeans, right-click on the project and select **Run**. This will execute the main application and allow you to follow the on-screen instructions to:
     - Encrypt an image using RSA and Vigenère encryption.
     - Decrypt an encrypted image back to its original form.

2. **Follow the prompts** within the application to select an image for encryption or decryption.

---

## How It Works

### **RSA Encryption**
RSA is a public-key encryption algorithm that encrypts image data using a key pair (public and private keys). Only the private key can decrypt the image, ensuring its security.

### **Vigenère Cipher**
This classical cipher encrypts textual components of the image, such as metadata or the image name, using a repeating keyword, adding an extra layer of encryption.

---

## Inspiration
This project merges concepts from a past academic project with cryptographic principles learned during college. It serves as a hands-on exploration of both modern and classical encryption algorithms.
