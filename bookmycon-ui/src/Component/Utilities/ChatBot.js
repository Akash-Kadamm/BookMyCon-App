import React from 'react'
import '../../App.css';
import {useState} from 'react'
import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import {MainContainer,ChatContainer,MessageList,Message,MessageInput,TypingIndicator} from "@chatscope/chat-ui-kit-react"

const API_KEY="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";



export default function ChatBot() {
    const[typing,setTyping]=useState(false);
    const[messages,setMessages]=useState([
        {
               message:"Hello i am Assistant, How may i help you?",
               sender:"Solution Assistant"
        }
    ]);

    const handelSend= async (message)=>{
        const newMessage={
            message:message,
            sender:"user",
            direction:"outgoing"
        }

        const newMessages=[...messages,newMessage]; // all the old messages + new messages.

        // update our message state
        setMessages(newMessages);

        // Set a typing indicator..
        setTyping(true);
         
        // process message to chatGpt(send it over and see the response.)
        await processMessageToAssistant(newMessages);

    }

   async function processMessageToAssistant(chatMessages){
     // chatMessages{sender:"user" or "assistant", message:"The message content here."}
     // apiMessage{role:"user" or "assistant", content:"The message content here."}

     let apiMessages= chatMessages.map((messageObject)=>{
         let role="";
         if(messageObject.sender === "Solution Assistant"){
             role="assistant"
         }else{
             role="user"
         }
         return {role: role,content:messageObject.message }
     });

     // role:"user" -> a message from the user,"assistant" -> a response from assistant.
     // "system" -> generally one inital message defininghow we want assistant to talk.

     const systemMessage={
         role:"system",
         content:"Explain allconcept like i am 10 years old." // speak like pirate
     }

       const apiRequestBody={
           "model":"gpt-3.5-turbo",
           "messages":[
               ...apiMessages //[message1,message2,message3]
           ]
       }

     await fetch("https://api.openai.com/v1/chat/completions",{
         method:"POST",
         headers:{
             "Authorization": "Bearer "+API_KEY,
             "Content-Type":"application/json"
         },
         body: JSON.stringify(apiRequestBody)
     }).then((data)=>{
         return data.json();
     }).then((data)=>{
         console.log(data);
         console.log(data.choices[0].message.content);
         setMessages(
             [...chatMessages,{
                 message:data.choices[0].message.content,
                 sender:"Solution Assistant"
             }]
         );
         setTyping(false);
     });
   }

  return (
      <div className="App">
        <div style={{position:"center",height:"600px",width:"800px"}}>
            <MainContainer >
                <ChatContainer id="chatcontainer" >
                    <MessageList
                    id="messagelist"
                    scrollBehavior='smooth'
                    typingIndicator={typing ? <TypingIndicator content="Assistant is typing...."/>: null}
                    >
                        {messages.map((message,i)=>{
                            return <Message key={i} model={message}/>
                        })}
                    </MessageList>
                    <MessageInput id="messageinput" placeholder="Type message here.." onSend={handelSend}/>
                </ChatContainer>
            </MainContainer>


        </div>
      </div>
        
  )
}
