
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medicine & Pharmacy Management System</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f4f7fb;
            color: #1f2937;
        }

        .navbar {
            background: #1769aa;
            color: white;
            padding: 22px 5%;
        }

        .navbar h1 {
            font-size: 25px;
        }

        .container {
            width: 92%;
            max-width: 1250px;
            margin: 35px auto;
        }

        .welcome {
            margin-bottom: 28px;
        }

        .welcome h2 {
            font-size: 30px;
            margin-bottom: 10px;
        }

        .welcome p {
            color: #64748b;
            line-height: 1.6;
        }

        .summary {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 18px;
            margin-bottom: 35px;
        }

        .summary-card {
            background: white;
            padding: 22px;
            border-radius: 12px;
            box-shadow: 0 4px 12px #0000000d;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .summary-icon {
            font-size: 32px;
        }

        .summary-card h3 {
            font-size: 25px;
            margin-bottom: 5px;
        }

        .summary-card p {
            font-size: 13px;
            color: #64748b;
        }

        .section-title {
            font-size: 23px;
            margin-bottom: 18px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 18px;
            margin-bottom: 35px;
        }

        .card {
            display: block;
            background: white;
            padding: 24px;
            border-radius: 12px;
            box-shadow: 0 4px 12px #0000000d;
            text-decoration: none;
            color: #1f2937;
            transition: 0.2s;
        }

        .card:hover {
            transform: translateY(-3px);
            box-shadow: 0 7px 18px #00000018;
        }

        .card .icon {
            font-size: 32px;
            margin-bottom: 14px;
        }

        .card h3 {
            font-size: 18px;
            margin-bottom: 9px;
        }

        .card p {
            color: #64748b;
            font-size: 14px;
            line-height: 1.6;
        }

        .section {
            background: white;
            padding: 26px;
            border-radius: 12px;
            box-shadow: 0 4px 12px #0000000d;
            margin-bottom: 28px;
        }

        .actions {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 14px;
        }

        .action {
            display: block;
            padding: 17px;
            border: 1px solid #e2e8f0;
            border-radius: 9px;
            color: #1769aa;
            text-decoration: none;
            font-weight: bold;
            line-height: 1.5;
            transition: 0.2s;
        }

        .action:hover {
            background: #eaf4ff;
            border-color: #1769aa;
        }

        .footer {
            text-align: center;
            padding: 25px;
            color: #64748b;
            font-size: 13px;
        }

        @media (max-width: 950px) {
            .summary {
                grid-template-columns: repeat(2, 1fr);
            }

            .cards, .actions {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 600px) {
            .summary, .cards, .actions {
                grid-template-columns: 1fr;
            }

            .welcome h2 {
                font-size: 25px;
            }

            .navbar h1 {
                font-size: 21px;
            }

            .section {
                padding: 18px;
            }
        }
    </style>
</head>

<body>

<div class="navbar">
    <h1>💊 Medicine & Pharmacy Management System</h1>
</div>

<div class="container">

    <div class="welcome">
        <h2>Welcome to the Pharmacy Portal</h2>
        <p>
            Manage medicines, monitor stock, track expiry dates,
            manage customer orders and access customer accounts.
        </p>
    </div>

    <!-- Dashboard Summary -->
    <div class="summary">

        <div class="summary-card">
            <div class="summary-icon">💊</div>
            <div>
                <h3>20</h3>
                <p>Total Medicines</p>
            </div>
        </div>

        <div class="summary-card">
            <div class="summary-icon">⚠️</div>
            <div>
                <h3>3</h3>
                <p>Low Stock Medicines</p>
            </div>
        </div>

        <div class="summary-card">
            <div class="summary-icon">🚚</div>
            <div>
                <h3>3</h3>
                <p>Suppliers</p>
            </div>
        </div>

        <div class="summary-card">
            <div class="summary-icon">🛒</div>
            <div>
                <h3>3+</h3>
                <p>Sample and Customer Orders</p>
            </div>
        </div>

    </div>

    <!-- Pharmacy Management -->
    <h2 class="section-title">Pharmacy Management</h2>

    <div class="cards">

        <a class="card" href="pharmacy">
            <div class="icon">💊</div>
            <h3>Medicine Management</h3>
            <p>Browse all 20 medicines, prices, quantities and expiry dates.</p>
        </a>

        <a class="card" href="pharmacy#stock">
            <div class="icon">📦</div>
            <h3>Stock Management</h3>
            <p>Check medicine availability and stock levels.</p>
        </a>

        <a class="card" href="pharmacy#expiry">
            <div class="icon">⏰</div>
            <h3>Expiry Tracking</h3>
            <p>Monitor expiry dates and identify medicines requiring attention.</p>
        </a>

        <a class="card" href="pharmacy#lowstock">
            <div class="icon">⚠️</div>
            <h3>Low Stock Alerts</h3>
            <p>Identify medicines that require restocking.</p>
        </a>

        <a class="card" href="suppliers">
            <div class="icon">🚚</div>
            <h3>Supplier Management</h3>
            <p>View supplier contact and company details.</p>
        </a>

        <a class="card" href="orders">
            <div class="icon">📋</div>
            <h3>Order Management</h3>
            <p>View sample orders and confirmed cart orders.</p>
        </a>

    </div>

    <!-- Shopping and Orders -->
    <div class="section">
        <h2 class="section-title">Shopping & Orders</h2>

        <div class="actions">
            <a class="action" href="cart">🛍️ Browse Medicines & Add to Cart</a>
            <a class="action" href="cart">🛒 View Cart & Place Order</a>
            <a class="action" href="orders">📦 Customer Order Management</a>
        </div>
    </div>

    <!-- Customer Portal -->
    <div class="section">
        <h2 class="section-title">Customer Portal</h2>

        <div class="actions">
            <a class="action" href="register">👤 Customer Registration</a>
            <a class="action" href="login">🔐 Customer Login</a>
            <a class="action" href="customer-dashboard">🏠 Customer Dashboard</a>
            <a class="action" href="profile">📝 Customer Profile</a>
            <a class="action" href="my-orders">📋 My Orders</a>
            <a class="action" href="logout">🚪 Customer Logout</a>
        </div>
    </div>

</div>

<div class="footer">
    © 2026 Medicine & Pharmacy Management System |
    Java Servlet Web Application
</div>

</body>
</html>
