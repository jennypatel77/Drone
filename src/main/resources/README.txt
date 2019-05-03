Drone Delivery Challenge
Overview
Implement an algorithm to schedule drone-carried deliveries to customers in a small town. The
town is organized in a perfect grid, with a warehouse and drone-launch facility at the center. All
deliveries originate at the warehouse and are carried by a drone to a customer location. A drone's
"ground speed" is exactly one horizontal or vertical grid block per minute.
Your homework assignment is to design and write a drone launch scheduling program that
maximizes the net promoter score (NPS) amongst drone-delivery customers. Net promoter
score is defined as the percentage of promoters minus the percentage of detractors. 
The town owns one drone, which is allowed to operate from 6 a.m. until 10 p.m.

Solution:

Step 1: Grab all the orders that have already been placed at current time. 
Step 2: Sort these orders based on their distance from the warehouse using the coordinates provided with each order.
Step 3: Pick the shortest delivery order and dispatch drone.
Step 4: Increment the current time to time when drone comes back after fulfilling the order
Step 5: Remove the order that was fulfilled from the original list of orders.
Repeat through step 1-5

Special Case: If the drone has no orders to fill at current time
Step 1.a: Sort the orders that are still not placed based on time.
Step 1.b: Make drone rest at ware house until first order comes in.
Step 1.c: Immediately dispatch drone when first order arrives

Special Case: If two orders have the same delivery time when being evaluated
Step 1.a: Pick the order with the lowest order number

Assumptions:
1. Only one file of orders come in per day.
2. There is only one functional drone.
3. When orders file is provided as input, it is aware of futher orders too.
4. Drone is not bound by the grid and can fly diagonally 


Instructions to run the project:
1. Go to the target folder of the project.
2. Launch GIT BASH
3. Execute java -jar drone-challenge-0.0.1-SNAPSHOT-jar-with-dependencies.jar "file full location"
4. If successful it will return with: Your result could be found at: 'output file location'
