const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");

// MongoDB Connection
const MONGO_URI = "mongodb+srv://ktchandru1234:k.t.chandru1234@cluster0.afircsf.mongodb.net/identitydb";
mongoose.connect(MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => console.log("MongoDB Connected"))
    .catch(err => console.error("MongoDB Connection Error:", err));

const app = express();
app.use(bodyParser.json());

// Contact Schema
const contactSchema = new mongoose.Schema({
    phoneNumber: { type: String, required: false },
    email: { type: String, required: false },
    linkedId: { type: mongoose.Schema.Types.ObjectId, default: null },
    linkPrecedence: { type: String, enum: ["primary", "secondary"], required: true },
    createdAt: { type: Date, default: Date.now },
    updatedAt: { type: Date, default: Date.now },
    deletedAt: { type: Date, default: null }
});

const Contact = mongoose.model("Contact", contactSchema);

// API Endpoint: Identify Contact
app.post("/api/identify", async (req, res) => {
    const { email, phoneNumber } = req.body;

    if (!email && !phoneNumber) {
        return res.status(400).json({ message: "Email or phoneNumber is required." });
    }

    const contacts = await db.collection("contacts").find({
        $or: [{ email }, { phoneNumber }]
    }).toArray();

    let primaryContact;
    if (contacts.length > 0) {
        primaryContact = contacts.find(c => c.linkPrecedence === "primary") || contacts[0];
    } else {
        // Create a new primary contact
        primaryContact = await db.collection("contacts").insertOne({
            email,
            phoneNumber,
            linkPrecedence: "primary",
            createdAt: new Date(),
            updatedAt: new Date()
        });

        primaryContact._id = primaryContact.insertedId;
    }

    const secondaryContacts = contacts.filter(c => c.linkPrecedence === "secondary");

    // Construct response
    const response = {
        primaryContactId: primaryContact._id.toString(),
        emails: [...new Set([primaryContact.email, ...secondaryContacts.map(c => c.email)].filter(Boolean))],
        phoneNumbers: [...new Set([primaryContact.phoneNumber, ...secondaryContacts.map(c => c.phoneNumber)].filter(Boolean))],
        secondaryContactIds: secondaryContacts.map(c => c._id.toString())
    };

    res.json(response);
});

// Format Response
const formatResponse = (primary, contacts) => {
    return {
        primaryContactId: primary._id,
        emails: [...new Set(contacts.map(c => c.email).filter(Boolean))],
        phoneNumbers: [...new Set(contacts.map(c => c.phoneNumber).filter(Boolean))],
        secondaryContactIds: contacts.filter(c => c.linkPrecedence === "secondary").map(c => c._id)
    };
};

// Start Server
const PORT = 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
