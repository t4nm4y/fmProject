import React, { FC } from 'react';
interface StepperProps {
    steps: {
        stepLabel: string | React.ReactNode;
        stepContent: string | React.ReactNode;
    }[];
    activeStep: number;
}
declare const Stepper: FC<StepperProps>;
export { Stepper };
