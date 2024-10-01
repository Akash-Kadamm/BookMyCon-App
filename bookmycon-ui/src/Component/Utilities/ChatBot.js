
import Chatbot from "react-chatbot-kit";
import 'react-chatbot-kit/build/main.css'
import config from "./Chatbot-files/config";
import MessageParser from "./Chatbot-files/MessageParser";
import ActionProvider from "./Chatbot-files/ActionProvider";
import "../../css/Chatbot.css"




import React from 'react'

const ChatBot = () => {
  return (
   <div className="chatbot-div">
   <Chatbot 
        config={config}
        messageParser={MessageParser}
        actionProvider={ActionProvider}
      />
   </div>
  )
}

export default ChatBot