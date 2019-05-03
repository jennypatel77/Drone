Drone Delivery Challenge
Overview
Implement an algorithm to schedule drone-carried deliveries to customers in a small town. The
town is organized in a perfect grid, with a warehouse and drone-launch facility at the center. All
deliveries originate at the warehouse and are carried by a drone to a customer location. A drone's
"ground speed" is exactly one horizontal or vertical grid block per minute.
Your homework assignment is to design and write a drone launch scheduling program that
maximizes the net promoter score (NPS) amongst drone-delivery customers. Net promoter
score is defined as the percentage of promoters minus the percentage of detractors. See the
diagram below to understand the relationship between delivery speed and customer feedback
responses. The town owns one drone, which is allowed to operate from 6 a.m. until 10 p.m.

Solution:

Step 1: Grab all the orders that were already placed in current time. 
Step 2: Sort these orders based on their distance from the warehouse using the coordinate provided with each order.
Step 3: Pick the shortest order and dispatch drone.
Step 4: Increment the current time to time when drone comes back after fulfilling the order
Step 5: Remove the order that was fulfilled from the orignal list of orders.
Repeat through step 1-5

Special Case: If all the orders were not fulfilled but also there are no orders placed in current time
Step 1.a: Sort the orders that are still not placed based on time.
Step 1.b: Make drone rest at ware house untill first order comes in.

Assumptions:
1. Only one file of orders come in per day.
2. There is only one functional drone.
3. When orders file is provided as input, it is aware of further orders too.


Instructions to run the project:
1. Go to the target folder of the project.
2. Launch GIT BASH
3. Execute java -jar drone-challenge-0.0.1-SNAPSHOT-jar-with-dependencies.jar "file full location"
4. If successful it will return with: Your result could be found at: 'output file location'



