import { createChatBotMessage } from "react-chatbot-kit";

import {StartBtn, FileBtn,FindBtn, RedirectBtn } from "./StartBtn";
const config = {
  initialMessages: [createChatBotMessage(`Hello, Welcome to BookMyCon.`,{
    widget:"startBtn"
  })],
  


  botName:"Solution Assistant",
  widgets: [
    {
      widgetName: 'startBtn',
      widgetFunc: (props) => <StartBtn {...props} />,
    },
    {
        widgetName: 'fileBtn',
        widgetFunc: (props) => <FileBtn{...props} />,
      },
      {
        widgetName:'pagesBtn',
        widgetFunc:(props)=><RedirectBtn{...props}/>
      }
  ],
}

export default config