import http from 'k6/http';
import {htmlReport} from "https://raw.githubusercontent.com/benc-uk/k6-reporter/2.4.0/dist/bundle.js";
import {check} from 'k6';
import {Rate} from 'k6/metrics';

const errorRate = new Rate('errorRate');

export const options = {
  // vus: 50,
  // iterations: 400
  stages: [
    {
      duration: '10s',
      target: 100
    },
    {
      duration: '30s',
      target: 200
    },
    {
      duration: '10s',
      target: 0
    }
  ]
};

export default function () {
  const url = 'http://localhost:8080/api/v1/genres';
  const payload = JSON.stringify({
    name: 'new genre'
  });
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };
  let response = http.post(url, payload, params);
  check(response, { 'status was 201': (response) => response.status === 201 });
  errorRate.add(response.status >= 400);
}

export function handleSummary(data) {
  return {
    "summary.html": htmlReport(data),
  };
}