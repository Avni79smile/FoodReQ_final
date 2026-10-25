import React, {useEffect, useState} from "react";
import {createRoot} from "react-dom/client";
import "./styles.css";

const G="/api";
async function api(path, options={}) {
  const headers = {...(options.headers || {})};
  if (options.body && !headers["Content-Type"]) headers["Content-Type"] = "application/json";
  const r=await fetch(G+path,{...options,headers});
  if(!r.ok) throw new Error(await r.text());
  return r.json();
}

function App(){
 const [tab,setTab]=useState("overview");
 const [donors,setDonors]=useState([]);
 const [recipients,setRecipients]=useState([]);
 const [donations,setDonations]=useState([]);
 const [matches,setMatches]=useState([]);
 const [notice,setNotice]=useState("");
 const [donorForm,setDonorForm]=useState({name:"",email:"",phone:"",location:"Mumbai"});
 const [recipientForm,setRecipientForm]=useState({name:"",email:"",phone:"",location:"Mumbai",urgency:"HIGH",capacityKg:50,pickupAvailable:true});
 const [donationForm,setDonationForm]=useState({donorId:"",foodType:"Cooked Meals",quantityKg:25,location:"Mumbai",expiryTime:"",status:"AVAILABLE"});
 const [matchForm,setMatchForm]=useState({donationId:"demo-donation",recipientId:"demo-recipient",distanceScore:80,expiryScore:85,quantityScore:90,urgencyScore:95,pickupScore:100,distance_km:4,expiry_hours:10,quantity_ratio:1,urgency_score:95,pickup_available:1,recipient_capacity_kg:50,historical_acceptance_rate:.72});

 async function load(){
  try{
   const [d,r,do_,m]=await Promise.all([
    api("/donor-service/donors"),api("/recipient-service/recipients"),api("/donation-service/donations"),api("/matching-service/matches")
   ]);
   setDonors(d);setRecipients(r);setDonations(do_);setMatches(m);
  }catch(e){setNotice("Backend not ready yet. Start the services first.");}
 }
 useEffect(()=>{load()},[]);
 const create=async(path,body)=>{try{await api(path,{method:"POST",body:JSON.stringify(body)});setNotice("Saved successfully.");load()}catch(e){setNotice(e.message)}};
 const runMatch=async()=>{try{const x=await api("/matching-service/matches/score",{method:"POST",body:JSON.stringify(matchForm)});setMatches([x,...matches]);setNotice("Smart ML match calculated.");}catch(e){setNotice("Matching/ML service is not ready.");}};
 const cards=[["Donors",donors.length,"🤝"],["Recipients",recipients.length,"🏠"],["Donations",donations.length,"🍱"],["Matches",matches.length,"🧠"]];

 return <div className="app">
  <aside>
   <div className="brand"><div className="logo">F</div><div><b>FoodResQ</b><span>Smart food redistribution</span></div></div>
   {["overview","donors","recipients","donations","matching","logistics","notifications"].map(x=><button className={tab===x?"nav active":"nav"} onClick={()=>setTab(x)} key={x}>{x==="overview"?"◈":x==="donors"?"♙":x==="recipients"?"⌂":x==="donations"?"▣":x==="matching"?"✦":x==="logistics"?"↗":"◉"} {x[0].toUpperCase()+x.slice(1)}</button>)}
   <div className="sidefoot"><small>Microservices</small><strong>10 services + ML</strong><small>Gateway · Eureka · Config</small></div>
  </aside>
  <main>
   <header><div><p className="eyebrow">FOOD RESCUE COMMAND CENTER</p><h1>{tab==="overview"?"Good food. Better matches.":tab[0].toUpperCase()+tab.slice(1)}</h1><p className="muted">Monitor donations, intelligent matching and delivery in one place.</p></div><button className="refresh" onClick={load}>↻ Refresh</button></header>
   {notice&&<div className="notice">{notice}</div>}
   {tab==="overview"&&<><section className="hero"><div><span className="pill">LIVE PLATFORM</span><h2>Turn surplus into impact.</h2><p>FoodResQ ranks recipients using explainable matching factors and a trained ML acceptance model.</p><button onClick={()=>setTab("matching")}>Run smart matching →</button></div><div className="heroart"><div className="orb">🍱</div><div className="spark s1">✦</div><div className="spark s2">✦</div></div></section>
   <div className="cards">{cards.map(c=><div className="card" key={c[0]}><span>{c[2]}</span><small>{c[0]}</small><b>{c[1]}</b><em>Connected to gateway</em></div>)}</div>
   <section className="grid2"><div className="panel"><h3>How the intelligence works</h3><div className="flow">{["Donation","Rule score","ML probability","Final ranking"].map((x,i)=><div className="flowitem" key={x}><strong>{i+1}</strong><span>{x}</span></div>)}</div></div><div className="panel"><h3>System status</h3><div className="status"><i></i> API Gateway <b>8080</b></div><div className="status"><i></i> Eureka <b>8761</b></div><div className="status"><i></i> ML Service <b>8090</b></div><div className="status"><i></i> Config Server <b>8888</b></div></div></section></>}
   {tab==="donors"&&<><Form title="Add donor" fields={donorForm} set={setDonorForm} submit={()=>create("/donor-service/donors",donorForm)}/><Table rows={donors} cols={["name","email","phone","location"]}/></>}
   {tab==="recipients"&&<><Form title="Add recipient" fields={recipientForm} set={setRecipientForm} submit={()=>create("/recipient-service/recipients",recipientForm)}/><Table rows={recipients} cols={["name","email","location","urgency","capacityKg"]}/></>}
   {tab==="donations"&&<><Form title="Create food donation" fields={donationForm} set={setDonationForm} submit={()=>create("/donation-service/donations",donationForm)}/><Table rows={donations} cols={["foodType","quantityKg","location","expiryTime","status"]}/></>}
   {tab==="matching"&&<><div className="panel"><h3>Smart matching engine</h3><p className="muted">70% explainable rules + 30% ML acceptance probability.</p><div className="formgrid">{Object.entries(matchForm).map(([k,v])=><label key={k}>{k.replaceAll("_"," ")}<input value={v} onChange={e=>setMatchForm({...matchForm,[k]:e.target.value})}/></label>)}</div><button onClick={runMatch}>Calculate smart match</button></div><Table rows={matches} cols={["donationId","recipientId","ruleScore","mlProbability","finalScore","status","explanation"]}/></>}
   {tab==="logistics"&&<div className="panel"><h3>Logistics control</h3><p>Use the Logistics API to schedule, confirm and complete pickups.</p><a href="http://localhost:8086/swagger-ui/index.html" target="_blank">Open Logistics Swagger ↗</a></div>}
   {tab==="notifications"&&<div className="panel"><h3>Notifications</h3><p>In-app notification records are managed by Notification Service.</p><a href="http://localhost:8087/swagger-ui/index.html" target="_blank">Open Notification Swagger ↗</a></div>}
  </main>
 </div>
}
function Form({title,fields,set,submit}){return <div className="panel"><h3>{title}</h3><div className="formgrid">{Object.entries(fields).map(([k,v])=><label key={k}>{k.replaceAll("_"," ")}<input value={String(v??"")} onChange={e=>set({...fields,[k]:e.target.value})}/></label>)}</div><button onClick={submit}>Save</button></div>}
function Table({rows,cols}){return <div className="panel tablewrap"><h3>Records</h3>{rows.length===0?<div className="empty">No records yet.</div>:<table><thead><tr>{cols.map(c=><th key={c}>{c}</th>)}</tr></thead><tbody>{rows.map((r,i)=><tr key={i}>{cols.map(c=><td key={c}>{typeof r[c]==="object"?JSON.stringify(r[c]):String(r[c]??"—")}</td>)}</tr>)}</tbody></table>}</div>}
createRoot(document.getElementById("root")).render(<App/>);
