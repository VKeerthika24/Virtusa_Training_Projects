SmartPay Utility Biller

This project is a Core Java application developed to calculate electricity or water bills based on unit consumption. It uses a slab-based pricing system to encourage efficient usage of resources.

Features
- Calculates bill using progressive slab rates
- Validates meter readings to avoid incorrect input
- Uses interface implementation for clean structure
- Generates a formatted digital receipt
- Supports multiple customer entries until user exits

Slab Rates
0–100 units → $1 per unit
101–300 units → $2 per unit
Above 300 units → $5 per unit

How It Works

- The user enters customer details along with previous and current meter readings.
- The program calculates units consumed and applies slab rates accordingly.
- Then displays the total bill along with tax details in a receipt format.
- The process continues until the user types "Exit".

Output Screenshot
<h3>Generate Bill Output</h3>
<img src="images/generate new bill.png" width="600"/>

<h3>View Bills Output</h3>
<img src="images/view bills.png" width="600"/>
