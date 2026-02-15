let q=[],i=0,score=0,user="player";

async function login(){
    let email=document.getElementById("email").value;
    let pass=document.getElementById("pass").value;

    await fetch("/auth/login",{
        method:"POST",
        headers:{'Content-Type':'application/json'},
        body:JSON.stringify({email,password:pass})
    });

    alert("Login success");
}

async function load(){

    let c=document.getElementById("c").value;
    let l=document.getElementById("l").value;

    let r=await fetch(`/game/questions?c=${c}&l=${l}`);
    q=await r.json();

    i=0; score=0;
    show();
}

function show(){

    if(i>=q.length){

        fetch(`/game/score?user=${user}&pts=${score}`,{method:"POST"});

        game.innerHTML=`FINAL SCORE: ${score}`;
        return;
    }

    let x=q[i];

    game.innerHTML=
        `<h3>${x.questionText}</h3>
  ${btn(x.optionA)}
  ${btn(x.optionB)}
  ${btn(x.optionC)}
  ${btn(x.optionD)}`;
}

function btn(t){
    return `<button onclick="ans('${t}')">${t}</button>`;
}

function ans(a){

    if(a === questions[index].correctAnswer){

        score += 10;

    }else{

        score += 0;
    }

    index++;
    show();
}
function selectAnswer(btn, correctAnswer, question) {

    let selected = btn.dataset.value;

    saveProgress(question, selected, correctAnswer);

    let buttons = document.querySelectorAll(".answer-btn");
    buttons.forEach(b => b.disabled = true);

    if(selected === correctAnswer){

        btn.style.background = "green";

    }else{

        btn.style.background = "red";

        buttons.forEach(b=>{
            if(b.dataset.value === correctAnswer)
                b.style.background = "green";
        });
    }
}
function saveProgress(question, selected, correct) {

    fetch("/game/save-progress", {

        method: "POST",
        headers: {"Content-Type": "application/json"},

        body: JSON.stringify({
            userId: currentUserId,
            question: question,
            selectedAnswer: selected,
            correctAnswer: correct
        })

    });
}
