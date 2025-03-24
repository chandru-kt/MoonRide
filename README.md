# MoonRide

# 🚀 Task 1 - Identity Reconciliation Microservice

This microservice is built using **Node.js**, **Express**, and **MongoDB**, and provides an API for **contact consolidation** by linking related email and phone number records.

---

## 📌 Features
✅ **Identify and link contacts** (Primary & Secondary)  
✅ **MongoDB Integration** (Atlas Database)  
✅ **REST API with Express.js**  
✅ **Scalable Microservice Architecture**  

---

## 🏠 **Project Setup**
### **🔹 Clone Repository**
```sh
git clone <your-repository-url>
cd identity-reconciliation
```

### **🔹 Install Dependencies**
```sh
npm install
```

### **🔹 Set Up MongoDB Atlas**
1. **Create an account** on [MongoDB Atlas](https://www.mongodb.com/atlas).  
2. **Create a new cluster** and a **database named `identitydb`**.  
3. **Whitelist all IPs** (`0.0.0.0/0`).  
4. **Update `MONGO_URI` in `index.js`:**  
   ```js
   const MONGO_URI = "mongodb+srv://<username>:<password>@cluster.mongodb.net/identitydb";
   ```

---

## 🐙 **Run the Microservice**
### **🔹 Start the Server**
```sh
node index.js
```
✅ **Expected Output:**  
```
MongoDB Connected
Server running on port 3000
```

---

## 💤 **API Endpoints**
### **1️⃣ Identify Contact**
**Method:** `POST`  
**URL:** `http://localhost:3000/api/identify`  
**Request Body:**  
```json
{
  "email": "test@example.com",
  "phoneNumber": "+1234567890"
}
```
**Response:**  
```json
{
  "primaryContactId": "660f5c8c9b3e2a6e7fef5a11",
  "emails": ["test@example.com"],
  "phoneNumbers": ["+1234567890"],
  "secondaryContactIds": []
}
```

---


## 🔧 **Testing & Debugging**
### **🔹 Use Postman to Test API**
- **POST Request** to `/api/identify`
- Provide an email and/or phone number
- Validate response in **MongoDB Atlas**

### **🔹 View MongoDB Logs**
```sh
mongosh "mongodb+srv://<username>:<password>@cluster.mongodb.net/identitydb"
db.contacts.find().pretty()
```

### **🔹 Check Server Logs**
```sh
node index.js
```

---


## 🏁 **Final Steps**
✅ **Test in Postman**  
✅ **Verify Data in MongoDB Atlas**  
✅ **Deploy to a Cloud Server (Optional)**  

---

## 🌟 **Next Steps**
- **Implement Caching (Redis) for Faster Lookups**  
- **Deploy to Kubernetes**  
- **Add Authentication (JWT)**  

---


# 🚀 Task2 - Spring Boot Microservice: Product Catalog API

This microservice is a **REST API for managing a product catalog**, built with **Spring Boot**, **MongoDB**, **Docker**, and **Kubernetes**.  
It includes **version management**, **CI/CD with GitHub Actions**, and **Kubernetes deployment with Minikube**.

---

## 📌 Features
✅ **CRUD API for Products** (Spring Boot + MongoDB)  
✅ **Dockerized Microservice** (Multi-stage build, Environment Variables)  
✅ **Kubernetes Deployment** (Namespace isolation, HPA, Ingress Controller)  
✅ **GitHub Actions CI/CD** (Automated Build, Push to Docker Hub, Deploy to Kubernetes)  

---

## 🏠 **Project Setup**
### **🔹 Clone Repository**
```sh
git clone <your-repository-url>
cd spring-boot-crud-example
```

### **🔹 Set Up MongoDB Atlas**
1. **Create an account** on [MongoDB Atlas](https://www.mongodb.com/atlas).  
2. **Create a new cluster** and a **database named `devops`**.  
3. **Whitelist all IPs** (`0.0.0.0/0`).  
4. **Get your MongoDB connection string** and update `application.properties`:  
   ```properties
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster.mongodb.net/devops
   ```

### **🔹 Install Dependencies**
```sh
mvn clean install -DskipTests
```

---

## 🐙 **Docker Containerization**
### **🔹 Build & Run Locally**
1. **Build Docker Image**  
   ```sh
   docker build -t my-microservice .
   ```
2. **Run the Container**  
   ```sh
   docker run --env-file .env -p 9090:9090 my-microservice
   ```

### **🔹 Push to Docker Hub**
```sh
docker tag my-microservice mydockerhub/my-microservice:v1.0.0
docker push mydockerhub/my-microservice:v1.0.0
```

---

## ☘️ **Kubernetes Deployment**
### **🔹 Start Minikube**
```sh
minikube start
```

### **🔹 Apply Namespace & Deploy**
```sh
kubectl apply -f deployment/namespace.yaml
kubectl apply -f deployment/deployment.yaml
kubectl apply -f deployment/service.yaml
kubectl apply -f deployment/ingress.yaml
```

### **🔹 Get Service URL**
```sh
minikube service microservice-service --url
```
Use this URL in **Postman** for testing.

---

## 💤 **API Endpoints**
| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| **GET** | `/api/products` | Get all products |
| **POST** | `/api/addProduct` | Add a new product |
| **GET** | `/api/productById/{id}` | Get product by ID |
| **GET** | `/api/product/{name}` | Get product by name |
| **PUT** | `/api/update` | Update a product |
| **DELETE** | `/api/delete/{id}` | Delete a product |

---

## 🛠️ **CI/CD Pipeline (GitHub Actions)**
### **🔹 What It Does?**
✅ **Build Docker Image**  
✅ **Push Image to Docker Hub**  
✅ **Deploy to Kubernetes**  

### **🔹 Setup GitHub Secrets**
1. **Go to GitHub → Repo → Settings → Secrets**  
2. **Add These Secrets:**
   - `DOCKER_USERNAME`: **Your Docker Hub username**
   - `DOCKER_PASSWORD`: **Your Docker Hub Access Token**

### **🔹 GitHub Actions Workflow**
The CI/CD pipeline is defined in **`.github/workflows/deploy.yml`**.



---

## 📊 **Monitoring & Logging**
1. **Check Kubernetes Logs**
   ```sh
   kubectl logs <pod-name>
   ```
2. **Enable Prometheus & Grafana in Minikube**
   ```sh
   minikube addons enable metrics-server
   ```

---

## 📝 **Troubleshooting**
| Issue | Solution |
|-------|----------|
| **Pods Stuck in `ErrImagePull`** | Run `minikube image load my-microservice` |
| **Service Not Reachable** | Restart Minikube: `minikube delete && minikube start` |
| **MongoDB Connection Failing** | Check `spring.data.mongodb.uri` in `application.properties` |

---

## 🏁 **Final Steps**
✅ **Test in Postman** using `minikube service microservice-service --url`  
✅ **Push to GitHub**  
✅ **Deploy to Kubernetes**  

---

## 🌟 **Next Steps**
- **Deploy to AWS/GCP Kubernetes**  
- **Improve Logging & Monitoring**  
- **Scale Microservice with Horizontal Pod Autoscaler (HPA)**  

---

## 🤝 **Contributing**
Pull requests are welcome!  

---

## 🐜 **License**
This project is licensed under **MIT License**.



