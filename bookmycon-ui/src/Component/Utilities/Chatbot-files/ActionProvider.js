import React from 'react';


const ActionProvider = ({ createChatBotMessage, setState, children }) => {
  
  
  const pageRedirectState=()=>{
    const message=createChatBotMessage("Please select the page you want.",{
      widget:"pagesBtn"
    })
    updateState(message)
  }
  
  
  const afterEmailState=()=>{
    const message=createChatBotMessage("'what you want to do?",{
      widget:"fileBtn"}
    )
    updateState(message)
  }

const notEmailState=()=>{
  const message=createChatBotMessage("Please enter your registered mail Id correctly")
updateState(message)
}


  const initialState=()=>{
    const message=createChatBotMessage('Just type your registered email to proceed')
    updateState(message,"email")
  }

const updateState=(message,checker="")=>{
  setState((prev)=>({
    ...prev,
    messages:[...prev.messages,message],
    checker
  }))
}

  return (
    <div>
      {React.Children.map(children, (child) => {
        return React.cloneElement(child, {
          actions: {
            initialState,
            afterEmailState,
            notEmailState,
            pageRedirectState
       
          },
        });
      })}
    </div>
  );
};

export default ActionProvider;