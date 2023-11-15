class BmiCalculate {
    static Calculate(info) {

        let payload = {
            height: 0,
            weight: 0,
            result: 0,
            resultName: ""
        }

        payload.height = info.height;
        payload.weight = info.weight;

        const result = info.weight / (info.height * info.height);
        payload.result = result.toFixed(2);

        if(result < 18.5) {
            payload.resultName = "Zayıf";
        }

        if(result >= 18.5 && result <= 22.9) {
            payload.resultName = "Normal";
        }

        if(result >= 23.0 && result <= 24.9) {
            payload.resultName = "Biraz Kilolu";
        }

        if(result >= 25.0 && result <= 29.9) {
            payload.resultName = "Kilolu";
        }

        if(result >= 30.0) {
            payload.resultName = "Obez";
        }
        
        return payload;
    }
}

export default BmiCalculate