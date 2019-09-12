package com.example.rabbitmq.notes;

public class Notes {

	/*- General Information
	 * 
	 * 
	 * RabbitMQ is based on AMQP [Advanced messaging queuing protocol]
	 * 
	 * Unlike JMS which has just 
	 * (Publisher) -> [Message Queue] -> (Consumer)
	 * 
	 * RabbitMQ has a exchange in between of publisher and message Queue
	 * (Publisher) -> [Exchange] -> [Message Queue] -> (consumer)
	 * 
	 * AMPQ has different kind of exchanges, common one's are
	 *  1. FanOut - Messages will go to all queues binded to exchange
	 *  2. Direct - Messages go to specific queue, evaluated based on 
	 *              binding key [between exchange and Queue] and 
	 *              routing key [between publisher and exchange].
	 *              If routing key and binding key matches then message 
	 *              ends up in the corresponding queue
	 *  3. Topic -  Here we can have wild cards for the binding keys.
	 *              This does a work of both of the above mentioned queues
	 *              example: shape.* [star can substitute for exactly one word]
	 *                       shape.# [hash can substitute for zero or more words]
	 *                       shape obviously is static ;)
	 *
	 * Exchange enhance the ECOSYSTEM of publisher and subscriber, 
	 * exchange can interact with multiple queues and provide multiple 
	 * opportunities for scaling.
	 * 
	 *
	 */
	
	
	/*- Installation Information on MAC Machine
	 *  
	 *   1. Install BREW first if not available already 
	 *   		ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
     *   
     *   2. Update the brew to latest
     *   		brew update
     *   
     *   3. Install RabbitMQ using brew
     *   		brew install rabbitmq
     *   
     *   4. Choose when to launch RabbitMq
     *      	a. brew services start rabbitmq   (start rabbitMQ now and restart at login)
     *      	b. rabbitmq-server                (Manual)
     *
	 * 
	 * Default starts at: http://localhost:15672
	 * Default userName/password is guest
	 * 
	 * 
	 */

}
