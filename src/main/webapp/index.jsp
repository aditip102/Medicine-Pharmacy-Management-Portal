<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Medicine & Pharmacy Management System</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        html {
            scroll-behavior: smooth;
        }

        body {
            background: #f4f7fb;
            color: #1f2937;
        }

        /* ================= NAVBAR ================= */

        .navbar {
            background: #1769aa;
            color: white;
            padding: 18px 50px;

            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h1 {
            font-size: 24px;
        }

        .navbar span {
            font-size: 14px;
            opacity: 0.9;
        }

        /* ================= CONTAINER ================= */

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 40px auto;
        }

        /* ================= WELCOME ================= */

        .welcome {
            margin-bottom: 30px;
        }

        .welcome h2 {
            font-size: 32px;
            margin-bottom: 8px;
        }

        .welcome p {
            color: #6b7280;
            font-size: 16px;
        }

        /* ================= SUMMARY ================= */

        .summary {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-bottom: 35px;
        }

        .summary-card {
            background: white;
            padding: 22px;
            border-radius: 12px;

            box-shadow: 0 4px 12px rgba(0,0,0,0.08);

            display: flex;
            align-items: center;
            gap: 18px;

            transition: 0.2s;
        }

        .summary-card:hover {
            transform: translateY(-3px);
        }

        .summary-icon {
            font-size: 32px;
        }

        .summary-info h3 {
            font-size: 24px;
            margin-bottom: 4px;
        }

        .summary-info p {
            color: #6b7280;
            font-size: 13px;
        }

        /* ================= FEATURE CARDS ================= */

        .cards {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-bottom: 35px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 12px;

            box-shadow: 0 4px 12px rgba(0,0,0,0.08);

            text-decoration: none;
            color: #1f2937;

            transition: 0.2s;
        }

        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 7px 18px rgba(0,0,0,0.12);
            background: #fafdff;
        }

        .card .icon {
            font-size: 32px;
            margin-bottom: 15px;
        }

        .card h3 {
            font-size: 17px;
            margin-bottom: 8px;
        }

        .card p {
            color: #6b7280;
            font-size: 14px;
            line-height: 1.5;
        }

        /* ================= QUICK ACTIONS ================= */

        .section {
            background: white;
            padding: 30px;
            border-radius: 12px;

            box-shadow: 0 4px 12px rgba(0,0,0,0.08);

            margin-bottom: 25px;
        }

        .section h2 {
            margin-bottom: 20px;
        }

        .actions {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 15px;
        }

        .action {
            padding: 18px;

            border: 1px solid #e5e7eb;
            border-radius: 10px;

            text-decoration: none;
            color: #1769aa;

            font-weight: bold;

            transition: 0.2s;
        }

        .action:hover {
            background: #eaf4ff;
            transform: translateY(-2px);
        }

        /* ================= FOOTER ================= */

        .footer {
            text-align: center;
            padding: 25px;

            color: #6b7280;
            font-size: 13px;
        }

        /* ================= RESPONSIVE ================= */

        @media (max-width: 1000px) {

            .summary {
                grid-template-columns: repeat(2, 1fr);
            }

            .cards {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 800px) {

            .navbar {
                padding: 18px 25px;
                flex-direction: column;
                gap: 8px;
                text-align: center;
            }

            .actions {
                grid-template-columns: 1fr;
            }
        }

        @media (max-width: 600px) {

            .summary {
                grid-template-columns: 1fr;
            }

            .cards {
                grid-template-columns: 1fr;
            }

            .container {
                width: 94%;
            }

            .welcome h2 {
                font-size: 26px;
            }
        }

    </style>

</head>

<body>

<!-- ================= NAVBAR ================= -->

<div class="navbar">

    <h1>💊 Medicine & Pharmacy Management System</h1>

</div>


<div class="container">

    <!-- ================= WELCOME ================= -->

    <div class="welcome">

        <h2>Welcome to the Medicine & Pharmacy Management System</h2>

        <p>
            Manage medicines, inventory, suppliers and customer orders
            from one place.
        </p>

    </div>


    <!-- ================= SUMMARY ================= -->

    <div class="summary">

        <div class="summary-card">

            <div class="summary-icon">
                💊
            </div>

            <div class="summary-info">

                <h3>4</h3>

                <p>Total Medicines</p>

            </div>

        </div>


        <div class="summary-card">

            <div class="summary-icon">
                ⚠️
            </div>

            <div class="summary-info">

                <h3>1</h3>

                <p>Low Stock Medicines</p>

            </div>

        </div>


        <div class="summary-card">

            <div class="summary-icon">
                🚚
            </div>

            <div class="summary-info">

                <h3>3</h3>

                <p>Suppliers</p>

            </div>

        </div>


        <div class="summary-card">

            <div class="summary-icon">
                🛒
            </div>

            <div class="summary-info">

                <h3>3</h3>

                <p>Customer Orders</p>

            </div>

        </div>

    </div>


    <!-- ================= FEATURE CARDS ================= -->

    <div class="cards">

        <!-- MEDICINE MANAGEMENT -->

        <a class="card" href="pharmacy#medicines">

            <div class="icon">
                💊
            </div>

            <h3>
                Medicine Management
            </h3>

            <p>
                View and manage available medicines,
                prices, stock and expiry information.
            </p>

        </a>


        <!-- STOCK MANAGEMENT -->

        <a class="card" href="pharmacy#stock">

            <div class="icon">
                📦
            </div>

            <h3>
                Stock Management
            </h3>

            <p>
                Check medicine availability and
                monitor current stock levels.
            </p>

        </a>


        <!-- EXPIRY TRACKING -->

        <a class="card" href="pharmacy#expiry">

            <div class="icon">
                ⏰
            </div>

            <h3>
                Expiry Tracking
            </h3>

            <p>
                Monitor medicine expiry dates and
                identify medicines requiring attention.
            </p>

        </a>


        <!-- LOW STOCK -->

        <a class="card" href="pharmacy#lowstock">

            <div class="icon">
                ⚠️
            </div>

            <h3>
                Low Stock Alerts
            </h3>

            <p>
                Identify medicines with low stock
                and determine restocking requirements.
            </p>

        </a>

    </div>


    <!-- ================= QUICK ACTIONS ================= -->

    <div class="section">

        <h2>
            Quick Actions
        </h2>


        <div class="actions">


            <!-- SEARCH -->

            <a class="action" href="pharmacy#search">

                🔍 Search / View Medicines

            </a>


            <!-- STOCK -->

            <a class="action" href="pharmacy#stock">

                📦 Check Stock

            </a>


            <!-- EXPIRY -->

            <a class="action" href="pharmacy#expiry">

                ⏰ Expiry Tracking

            </a>


            <!-- LOW STOCK -->

            <a class="action" href="pharmacy#lowstock">

                ⚠️ Low Stock Alerts

            </a>


            <!-- SUPPLIERS -->

            <a class="action" href="suppliers">

                🚚 Supplier Management

            </a>


            <!-- ORDERS -->

            <a class="action" href="orders">

                🛒 Customer Orders

            </a>


        </div>

    </div>


</div>


<!-- ================= FOOTER ================= -->

<div class="footer">

    © 2026 Medicine & Pharmacy Management System
    | Java Web Application

</div>


</body>

</html>