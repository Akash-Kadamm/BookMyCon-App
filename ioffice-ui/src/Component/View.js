import React from 'react'
import ReactTable from "react-table-6";  
import "react-table-6/react-table.css" 
import { useEffect, useState } from 'react'
import axios from 'axios'
import { style } from '@mui/system';
import { Button } from '@mui/material';
import { useNavigate } from "react-router-dom";
import { ReactSession } from 'react-client-session';
export const View = () => {
    
  const navigate = useNavigate();
    let [booking,setbooking]=useState([]);
    let [roomName,setroomName]=useState();
    let[errorMsg,setErrorMsg]=useState('');
    useEffect(()=>
    {
         getAllBooking();
    }, []);

    function handleBooking(roomName) {
        alert(`hello, ${roomName}`);
        ReactSession.setStoreType("localStorage");
        ReactSession.set("roomname",  {roomName});
        navigate("/auditorium-Booking");
      }
      

    
    const getAllBooking=()=>{
      axios
      //  .get("https://jsonplaceholder.typicode.com/users")
        .get("http://localhost:8080/admins/getAllBookings")
        .then(response => setbooking(response.data)).catch((error=>setErrorMsg("error ")));
   
    }
   

        const data = []
        let data1 = {}
        data1=booking;
        data1.forEach((e)=>{
      if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="10:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'Book',
        s10: 'NotBook',
        s11: 'NotBook',
        s12: 'NotBook',
        s1: 'NotBook',
        s2: 'NotBook',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
      else  if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="11:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'Book',
            s11: 'NotBook',
            s12: 'NotBook',
            s1: 'NotBook',
            s2: 'NotBook',
            s3: 'NotBook',
            s4: 'NotBook',
            s5: 'NotBook',
            s6: e.aduitoriamId.auditoriumName,
          })
            }
         else   if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="12:00:00"){ 
                data.push({
                task: e.aduitoriamId.auditoriumName,
                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                s9: 'NotBook',
                s10: 'NotBook',
                s11: 'Book',
                s12: 'NotBook',
                s1: 'NotBook',
                s2: 'NotBook',
                s3: 'NotBook',
                s4: 'NotBook',
                s5: 'NotBook',
                s6:  e.aduitoriamId.auditoriumName,
              })
                }
             else   if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="13:00:00"){ 
                    data.push({
                    task: e.aduitoriamId.auditoriumName,
                    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                    s9: 'NotBook',
                    s10: 'NotBook',
                    s11: 'NotBook',
                    s12: 'Book',
                    s1: 'NotBook',
                    s2: 'NotBook',
                    s3: 'NotBook',
                    s4: 'NotBook',
                    s5: 'NotBook',
                    s6:  e.aduitoriamId.auditoriumName,
                  })
                    }
                 else   if(e.bookingTimeFrom>="13:00:00" && e.bookingTimeTO<="14:00:00"){ 
                        data.push({
                        task: e.aduitoriamId.auditoriumName,
                        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                        s9: 'NotBook',
                        s10: 'NotBook',
                        s11: 'NotBook',
                        s12: 'NotBook',
                        s1: 'Book',
                        s2: 'NotBook',
                        s3: 'NotBook',
                        s4: 'NotBook',
                        s5: 'NotBook',
                        s6:  e.aduitoriamId.auditoriumName,
                      })
                        }
                    else    if(e.bookingTimeFrom>="14:00:00" && e.bookingTimeTO<="15:00:00"){ 
                            data.push({
                            task: e.aduitoriamId.auditoriumName,
                            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                            s9: 'NotBook',
                            s10: 'NotBook',
                            s11: 'NotBook',
                            s12: 'NotBook',
                            s1: 'NotBook',
                            s2: 'Book',
                            s3: 'NotBook',
                            s4: 'NotBook',
                            s5: 'NotBook',
                            s6: e.aduitoriamId.auditoriumName,
                          })
                            }
                      else      if(e.bookingTimeFrom>="15:00:00" && e.bookingTimeTO<="16:00:00"){ 
                                data.push({
                                task: e.aduitoriamId.auditoriumName,
                                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                                s9: 'NotBook',
                                s10: 'NotBook',
                                s11: 'NotBook',
                                s12: 'NotBook',
                                s1: 'NotBook',
                                s2: 'NotBook',
                                s3: 'Book',
                                s4: 'NotBook',
                                s5: 'NotBook',
                                s6:  e.aduitoriamId.auditoriumName,
                              })
                                }
                            else    if(e.bookingTimeFrom>="16:00:00" && e.bookingTimeTO<="17:00:00"){ 
                                    data.push({
                                    task: e.aduitoriamId.auditoriumName,
                                    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                                    s9: 'NotBook',
                                    s10: 'NotBook',
                                    s11: 'NotBook',
                                    s12: 'NotBook',
                                    s1: 'NotBook',
                                    s2: 'NotBook',
                                    s3: 'NotBook',
                                    s4: 'Book',
                                    s5: 'NotBook',
                                    s6:  e.aduitoriamId.auditoriumName,
                                  })
                                    }
                                    else if(e.bookingTimeFrom>="17:00:00" && e.bookingTimeTO<="18:00:00"){ 
                                        data.push({
                                        task: e.aduitoriamId.auditoriumName,
                                        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,  agenda:e.bookingAgenda,
                                        s9: 'NotBook',
                                        s10: 'NotBook',
                                        s11: 'NotBook',
                                        s12: 'NotBook',
                                        s1: 'NotBook',
                                        s2: 'NotBook',
                                        s3: 'NotBook',
                                        s4: 'NotBook',
                                        s5: 'Book',
                                        s6:  e.aduitoriamId.auditoriumName,
                                      })
                                        }

///-=-=-===-=-=-=-=-=-

else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="11:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'NotBook',
    s12: 'NotBook',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="12:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'NotBook',
        s1: 'NotBook',
        s2: 'NotBook',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }

        
else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="13:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'Book',
    s12: 'Book',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    
else if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="14:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'NotBook',
    s12: 'Book',
    s1: 'Book',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    
else if(e.bookingTimeFrom>="13:00:00" && e.bookingTimeTO<="15:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'NotBook',
    s12: 'NotBook',
    s1: 'Book',
    s2: 'Book',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    
else if(e.bookingTimeFrom>="14:00:00" && e.bookingTimeTO<="16:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'NotBook',
    s12: 'NotBook',
    s1: 'NotBook',
    s2: 'Book',
    s3: 'Book',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    
else if(e.bookingTimeFrom>="15:00:00" && e.bookingTimeTO<="17:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'NotBook',
    s12: 'NotBook',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'Book',
    s4: 'Book',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }

    
else if(e.bookingTimeFrom>="16:00:00" && e.bookingTimeTO<="18:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'NotBook',
    s10: 'NotBook',
    s11: 'NotBook',
    s12: 'NotBook',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'Book',
    s5: 'Book',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    

// -=-=--=-==-==-=-
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="12:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'NotBook',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="13:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'NotBook',
        s2: 'NotBook',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
        
        else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="14:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'NotBook',
            s11: 'Book',
            s12: 'Book',
            s1: 'Book',
            s2: 'NotBook',
            s3: 'NotBook',
            s4: 'NotBook',
            s5: 'NotBook',
            s6:  e.aduitoriamId.auditoriumName,
          })
            }
            
            else if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="15:00:00"){ 
                data.push({
                task: e.aduitoriamId.auditoriumName,
                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                s9: 'NotBook',
                s10: 'NotBook',
                s11: 'NotBook',
                s12: 'Book',
                s1: 'Book',
                s2: 'Book',
                s3: 'NotBook',
                s4: 'NotBook',
                s5: 'NotBook',
                s6:  e.aduitoriamId.auditoriumName,
              })
                }
                
                else if(e.bookingTimeFrom>="13:00:00" && e.bookingTimeTO<="16:00:00"){ 
                    data.push({
                    task: e.aduitoriamId.auditoriumName,
                    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                    s9: 'NotBook',
                    s10: 'NotBook',
                    s11: 'NotBook',
                    s12: 'NotBook',
                    s1: 'Book',
                    s2: 'Book',
                    s3: 'Book',
                    s4: 'NotBook',
                    s5: 'NotBook',
                    s6:  e.aduitoriamId.auditoriumName,
                  })
                    }
                    
                    else if(e.bookingTimeFrom>="14:00:00" && e.bookingTimeTO<="17:00:00"){ 
                        data.push({
                        task: e.aduitoriamId.auditoriumName,
                        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                        s9: 'NotBook',
                        s10: 'NotBook',
                        s11: 'NotBook',
                        s12: 'NotBook',
                        s1: 'NotBook',
                        s2: 'Book',
                        s3: 'Book',
                        s4: 'Book',
                        s5: 'NotBook',
                        s6:  e.aduitoriamId.auditoriumName,
                      })
                        }
                        
                        else if(e.bookingTimeFrom>="15:00:00" && e.bookingTimeTO<="18:00:00"){ 
                            data.push({
                            task: e.aduitoriamId.auditoriumName,
                            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                            s9: 'NotBook',
                            s10: 'NotBook',
                            s11: 'NotBook',
                            s12: 'NotBook',
                            s1: 'NotBook',
                            s2: 'NotBook',
                            s3: 'Book',
                            s4: 'Book',
                            s5: 'Book',
                            s6: e.aduitoriamId.auditoriumName,
                          })
                            }
                            
                                                                                                                                                
//--===-=-=--=
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="13:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'NotBook',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="14:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'Book',
        s2: 'NotBook',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
        else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="15:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'NotBook',
            s11: 'Book',
            s12: 'Book',
            s1: 'Book',
            s2: 'Book',
            s3: 'NotBook',
            s4: 'NotBook',
            s5: 'NotBook',
            s6:  e.aduitoriamId.auditoriumName,
          })
            }
            else if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="16:00:00"){ 
                data.push({
                task: e.aduitoriamId.auditoriumName,
                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                s9: 'NotBook',
                s10: 'NotBook',
                s11: 'NotBook',
                s12: 'Book',
                s1: 'Book',
                s2: 'Book',
                s3: 'Book',
                s4: 'NotBook',
                s5: 'NotBook',
                s6: e.aduitoriamId.auditoriumName,
              })
                }
                else if(e.bookingTimeFrom>="13:00:00" && e.bookingTimeTO<="17:00:00"){ 
                    data.push({
                    task: e.aduitoriamId.auditoriumName,
                    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                    s9: 'NotBook',
                    s10: 'NotBook',
                    s11: 'NotBook',
                    s12: 'NotBook',
                    s1: 'Book',
                    s2: 'Book',
                    s3: 'Book',
                    s4: 'Book',
                    s5: 'NotBook',
                    s6:  e.aduitoriamId.auditoriumName,
                  })
                    }
                    else if(e.bookingTimeFrom>="14:00:00" && e.bookingTimeTO<="18:00:00"){ 
                        data.push({
                        task: e.aduitoriamId.auditoriumName,
                        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                        s9: 'NotBook',
                        s10: 'NotBook',
                        s11: 'NotBook',
                        s12: 'NotBook',
                        s1: 'NotBook',
                        s2: 'Book',
                        s3: 'Book',
                        s4: 'Book',
                        s5: 'Book',
                        s6:  e.aduitoriamId.auditoriumName,
                      })
                        }
//-=-==-=-=-=-
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="14:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'Book',
    s2: 'NotBook',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="15:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'Book',
        s2: 'Book',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
        else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="16:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'NotBook',
            s11: 'Book',
            s12: 'Book',
            s1: 'Book',
            s2: 'Book',
            s3: 'Book',
            s4: 'NotBook',
            s5: 'NotBook',
            s6:  e.aduitoriamId.auditoriumName,
          })
            }
            else if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="17:00:00"){ 
                data.push({
                task: e.aduitoriamId.auditoriumName,
                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                s9: 'NotBook',
                s10: 'NotBook',
                s11: 'NotBook',
                s12: 'Book',
                s1: 'Book',
                s2: 'Book',
                s3: 'Book',
                s4: 'Book',
                s5: 'NotBook',
                s6:  e.aduitoriamId.auditoriumName,
              })
                }
                else if(e.bookingTimeFrom>="13:00:00" && e.bookingTimeTO<="18:00:00"){ 
                    data.push({
                    task: e.aduitoriamId.auditoriumName,
                    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                    s9: 'NotBook',
                    s10: 'NotBook',
                    s11: 'NotBook',
                    s12: 'NotBook',
                    s1: 'Book',
                    s2: 'Book',
                    s3: 'Book',
                    s4: 'Book',
                    s5: 'Book',
                    s6:  e.aduitoriamId.auditoriumName,
                  })
                    }

//-=-=-=-===-
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="15:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'Book',
    s2: 'Book',
    s3: 'NotBook',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="16:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'Book',
        s2: 'Book',
        s3: 'Book',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
        else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="17:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'NotBook',
            s11: 'Book',
            s12: 'Book',
            s1: 'Book',
            s2: 'Book',
            s3: 'Book',
            s4: 'Book',
            s5: 'NotBook',
            s6: e.aduitoriamId.auditoriumName,
          })
            }
            else if(e.bookingTimeFrom>="12:00:00" && e.bookingTimeTO<="18:00:00"){ 
                data.push({
                task: e.aduitoriamId.auditoriumName,
                datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
                s9: 'NotBook',
                s10: 'NotBook',
                s11: 'NotBook',
                s12: 'Book',
                s1: 'Book',
                s2: 'Book',
                s3: 'Book',
                s4: 'Book',
                s5: 'Book',
                s6:  e.aduitoriamId.auditoriumName,
              })
                }
//-=-=--==-==-==
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="16:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'Book',
    s2: 'Book',
    s3: 'Book',
    s4: 'NotBook',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="17:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'Book',
        s2: 'Book',
        s3: 'Book',
        s4: 'Book',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        }
        else if(e.bookingTimeFrom>="11:00:00" && e.bookingTimeTO<="18:00:00"){ 
            data.push({
            task: e.aduitoriamId.auditoriumName,
            datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
            s9: 'NotBook',
            s10: 'NotBook',
            s11: 'Book',
            s12: 'Book',
            s1: 'Book',
            s2: 'Book',
            s3: 'Book',
            s4: 'Book',
            s5: 'Book',
            s6:  e.aduitoriamId.auditoriumName,
          })
            }
//-=-=-==-=-==
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="17:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'Book',
    s2: 'Book',
    s3: 'Book',
    s4: 'Book',
    s5: 'NotBook',
    s6:  e.aduitoriamId.auditoriumName,
  })
    }
    else if(e.bookingTimeFrom>="10:00:00" && e.bookingTimeTO<="18:00:00"){ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'Book',
        s11: 'Book',
        s12: 'Book',
        s1: 'Book',
        s2: 'Book',
        s3: 'Book',
        s4: 'Book',
        s5: 'Book',
        s6: e.aduitoriamId.auditoriumName,
      })
        }
//=-=-=-=-=-=-
else if(e.bookingTimeFrom>="09:00:00" && e.bookingTimeTO<="18:00:00"){ 
    data.push({
    task: e.aduitoriamId.auditoriumName,
    datefrom: e.bookingDateFrom,
    dateto: e.bookingDateTo,
    agenda:e.bookingAgenda,
    s9: 'Book',
    s10: 'Book',
    s11: 'Book',
    s12: 'Book',
    s1: 'Book',
    s2: 'Book',
    s3: 'Book',
    s4: 'Book',
    s5: 'Book',
    s6:  e.aduitoriamId.auditoriumName,
  })
    } 

    else{ 
        data.push({
        task: e.aduitoriamId.auditoriumName,
        datefrom: e.bookingDateFrom,
        dateto: e.bookingDateTo,
        agenda:e.bookingAgenda,
        s9: 'NotBook',
        s10: 'NotBook',
        s11: 'NotBook',
        s12: 'NotBook',
        s1: 'NotBook',
        s2: 'NotBook',
        s3: 'NotBook',
        s4: 'NotBook',
        s5: 'NotBook',
        s6:  e.aduitoriamId.auditoriumName,
      })
        } 
    
//-=--=-=-==-=-
        })

  
    //     {
    //     task: 'Demo 1',
    //     status: 'Book'
    //   }, {
    //     task: 'Demo 2',
    //     status: 'NotBook'
    //   }
    
      
  
      const columns = [{
        Header: 'Room Name',
        accessor: 'task'
      }, 
      {
        Header: 'Date From',
        accessor: 'datefrom'
      }, 
      {
        Header: 'Date To',
        accessor: 'dateto'
      },  
      {
        Header: 'Agenda',
        accessor: 'agenda'
      }, {
        Header: '9AM-10AM',
        accessor: 's9',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
         if(row.value == 'Book'){return row.value.toUpperCase();}
            
        }
        
      }, {
        Header: '10AM-11AM',
        accessor: 's10',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '11AM-12PM',
        accessor: 's11',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '12PM-1PM',
        accessor: 's12',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '1PM-2PM',
        
        accessor: 's1',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '2PM-3PM',
        accessor: 's2',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '3PM-4PM',
        accessor: 's3',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: '4PM-5PM',
        accessor: 's4',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' :'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}
          
        }
        
      }, {
        Header: '5PM-6PM',
        accessor: 's5',
        Cell: row => {
          row.styles['color'] = '#fff';
          row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
          if(row.value == 'Book'){return row.value.toUpperCase();}

        }
        
      }, {
        Header: 'Book',
        accessor: 's6',
        Cell: row => {
       //   row.styles['color'] = '#fff';
        //  row.styles['backgroundColor'] = row.value == 'Book' ? 'gray' : 'white';
     //     return row.value.toUpperCase();
     //     if(row.value == 'Book'){return row.value.toUpperCase();}
        // setroomName(row.value)
       return <Button onClick={() => handleBooking(row.value)}>Book</Button>;
        }
        
      }];
    return (
    <div> <ReactTable
    
    data={data}
    columns={columns}
    defaultPageSize={5}
    className="-striped -highlight"
  /></div>
  )
}
